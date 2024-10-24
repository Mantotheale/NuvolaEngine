package nuvola.render.vertex.attribute;

import nuvola.render.vertex.layout.VertexAttributeTemplate;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;

public class Color4CAttribute implements VertexAttribute {
    private final float r, g, b, a;

    public Color4CAttribute(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    @Override
    public @NotNull VertexAttributeTemplate layout() {
        return VertexAttributeTemplate.COLOR_4C;
    }

    @Override
    public @NotNull ByteBuffer data() {
        ByteBuffer data = MemoryUtil.memAlloc(layout().size());
        data.asFloatBuffer().put(r).put(g).put(b).put(a);
        return data;
    }
}