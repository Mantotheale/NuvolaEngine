package nuvola.render.vertex;

import nuvola.render.vertex.attribute.VertexAttribute;
import nuvola.render.vertex.layout.VertexAttributeTemplate;
import nuvola.render.vertex.layout.VertexLayout;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Vertex {
    @NotNull private final List<VertexAttribute> attributes;
    @NotNull private final VertexLayout layout;

    public Vertex(@NotNull List<VertexAttribute> attributes) {
        this.attributes = Objects.requireNonNull(attributes);
        this.layout = calculateLayout(attributes);
    }

    public Vertex(@NotNull VertexAttribute ...attributes) {
        this(Arrays.asList(attributes));
    }

    @NotNull public VertexLayout layout() {
        return layout;
    }

    public int size() {
        return layout.size();
    }

    @NotNull public ByteBuffer data() {
        ByteBuffer data = MemoryUtil.memAlloc(size());

        for (VertexAttribute attribute: attributes) {
            ByteBuffer attributeData = attribute.data();
            data.put(attributeData);
            MemoryUtil.memFree(attributeData);
        }

        return data.flip();
    }

    @NotNull private static VertexLayout calculateLayout(@NotNull List<VertexAttribute> attributes) {
        List<VertexAttributeTemplate> attributeLayouts = new ArrayList<>();

        for (VertexAttribute attribute: attributes)
            attributeLayouts.add(attribute.layout());

        return new VertexLayout(attributeLayouts);
    }
}
