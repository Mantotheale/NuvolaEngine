package nuvola.render.vertex.attribute;

import nuvola.render.vertex.layout.VertexAttributeTemplate;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;

public class Color3CAttribute implements VertexAttribute {
    private final float r, g, b;

    public Color3CAttribute(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public @NotNull VertexAttributeTemplate layout() {
        return VertexAttributeTemplate.COLOR_3C;
    }

    @Override
    public @NotNull ByteBuffer data() {
        ByteBuffer data = MemoryUtil.memAlloc(layout().size());
        data.asFloatBuffer().put(r).put(g).put(b);
        return data;
    }
}