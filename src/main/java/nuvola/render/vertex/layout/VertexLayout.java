package nuvola.render.vertex.layout;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class VertexLayout implements Iterable<VertexAttributeTemplate> {
    @NotNull private final List<VertexAttributeTemplate> layout;
    private final int size;

    public VertexLayout(@NotNull List<VertexAttributeTemplate> layout) {
        this.layout = Objects.requireNonNull(layout);
        size = calculateSize(layout);
    }

    public int size() {
        return size;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof VertexLayout other) && layout.equals(other.layout));
    }

    private static int calculateSize(@NotNull List<VertexAttributeTemplate> layout) {
        int size = 0;

        for (VertexAttributeTemplate attributeLayout: layout)
            size += attributeLayout.size();

        return size;
    }

    @Override
    @NotNull public Iterator<VertexAttributeTemplate> iterator() {
        return layout.iterator();
    }
}
