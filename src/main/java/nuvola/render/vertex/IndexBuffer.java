package nuvola.render.vertex;

import org.jetbrains.annotations.NotNull;
import org.lwjgl.system.MemoryUtil;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;

public class IndexBuffer {
    private final int id;
    private final int count;

    public IndexBuffer(int... indices) {
        count = indices.length;

        IntBuffer buffer = loadBuffer(count, indices);

        id = glGenBuffers();
        bind();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);

        MemoryUtil.memFree(buffer);
    }

    public int id() {
        return id;
    }

    public int count() {
        return count;
    }

    public void bind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
    }

    public void unbind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    @NotNull private static IntBuffer loadBuffer(int count, int[] indices) {
        IntBuffer buffer = MemoryUtil.memAllocInt(count);

        for (int i: indices)
            buffer.put(i);

        return buffer.flip();
    }
}
