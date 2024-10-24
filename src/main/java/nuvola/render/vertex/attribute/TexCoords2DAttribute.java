package nuvola.render.vertex.attribute;

import nuvola.render.vertex.layout.VertexAttributeTemplate;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;

public class TexCoords2DAttribute implements VertexAttribute {
    private final float u, v;

    public TexCoords2DAttribute(float u, float v) {
        this.u = u;
        this.v = v;
    }

    @Override
    public @NotNull VertexAttributeTemplate layout() {
        return VertexAttributeTemplate.TEX_COORDS_2D;
    }

    @Override
    public @NotNull ByteBuffer data() {
        ByteBuffer data = MemoryUtil.memAlloc(layout().size());
        data.asFloatBuffer().put(u).put(v);
        return data;
    }
}