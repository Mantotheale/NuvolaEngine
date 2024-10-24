package nuvola.render.vertex.attribute;

import nuvola.render.vertex.layout.VertexAttributeTemplate;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;

public class TexCoords3DAttribute implements VertexAttribute {
    private final float u, v, w;

    public TexCoords3DAttribute(float u, float v, float w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    @Override
    public @NotNull VertexAttributeTemplate layout() {
        return VertexAttributeTemplate.TEX_COORDS_3D;
    }

    @Override
    public @NotNull ByteBuffer data() {
        ByteBuffer data = MemoryUtil.memAlloc(layout().size());
        data.asFloatBuffer().put(u).put(v).put(w);
        return data;
    }
}