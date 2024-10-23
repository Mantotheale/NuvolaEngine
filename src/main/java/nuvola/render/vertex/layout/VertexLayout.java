package nuvola.render.vertex.layout;

import nuvola.exceptions.vertex.VertexAttributeListIsEmptyException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class VertexLayout implements Iterable<VertexAttribute> {
    @NotNull private final List<VertexAttribute> attributes;
    private final int size;

    private VertexLayout(@NotNull List<VertexAttribute> attributes) {
        if (attributes.isEmpty())
            throw new VertexAttributeListIsEmptyException();

        this.attributes = attributes;

        VertexAttribute last = attributes.getLast();
        this.size = last.size() + last.offset();
    }

    public int size() {
        return size;
    }

    @Override
    @NotNull public Iterator<VertexAttribute> iterator() {
        return attributes.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append("Layout:\n");

        for (VertexAttribute attribute: attributes)
            sb.append("- ").append(attribute).append('\n');

        sb.append("Size: ").append(size);

        return sb.toString();
    }

    public static class Builder {
        @NotNull private final List<VertexAttribute> attributes;

        public Builder() {
            attributes = new ArrayList<>();
        }

        @NotNull public Builder add(@NotNull OpenGLType type, int count) {
            if (attributes.isEmpty()) {
                attributes.add(new VertexAttribute(new VertexAttributeTemplate(type, count), 0));
                return this;
            }

            VertexAttribute last = attributes.getLast();

            attributes.add(
                new VertexAttribute(
                    new VertexAttributeTemplate(type, count), last.size() + last.offset()
                )
            );

            return this;
        }

        @NotNull public Builder addTemplate(@NotNull VertexAttributeTemplate template) {
            if (attributes.isEmpty()) {
                attributes.add(new VertexAttribute(Objects.requireNonNull(template), 0));
                return this;
            }

            VertexAttribute last = attributes.getLast();

            attributes.add(new VertexAttribute(Objects.requireNonNull(template), last.size() + last.offset()));

            return this;
        }

        @NotNull public VertexLayout build() {
            return new VertexLayout(attributes);
        }
    }
}
