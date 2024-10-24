package nuvola.render.vertex.attribute;

import nuvola.render.vertex.layout.VertexAttributeTemplate;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;

public class Normal3DAttribute implements VertexAttribute {
    private final float x, y, z;

    public Normal3DAttribute(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public @NotNull VertexAttributeTemplate layout() {
        return VertexAttributeTemplate.NORMAL_3D;
    }

    @Override
    public @NotNull ByteBuffer data() {
        ByteBuffer data = MemoryUtil.memAlloc(layout().size());
        data.asFloatBuffer().put(x).put(y).put(z);
        return data;
    }
}