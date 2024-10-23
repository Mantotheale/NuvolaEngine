package nuvola.render.vertex.layout;

import nuvola.exceptions.vertex.OffsetInLayoutNegativeException;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record VertexAttribute(@NotNull VertexAttributeTemplate template, int offset) {
    public VertexAttribute {
        Objects.requireNonNull(template);

        if (offset < 0)
            throw new OffsetInLayoutNegativeException(offset);
    }

    public int count() {
        return  template.count();
    }

    public int size() {
        return template.size();
    }

    public int glEnum() {
        return template.openGLType().glEnum();
    }

    @Override
    public String toString() {
        return "{template: " + template + ", offset: " + offset +"}";
    }
}
