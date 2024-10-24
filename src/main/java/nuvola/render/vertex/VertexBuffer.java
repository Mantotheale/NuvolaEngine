package nuvola.render.vertex;

import nuvola.exceptions.vertex.VertexBufferNotHomogenousException;
import nuvola.render.vertex.layout.VertexLayout;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;

public class VertexBuffer {
    private final int id;
    private final VertexLayout layout;
    private final int vertexCount;

    public VertexBuffer(@NotNull Vertex ...vertices) {
        Objects.requireNonNull(vertices);

        if (!areAllLayoutsEqual(Arrays.stream(vertices).iterator()))
            throw new VertexBufferNotHomogenousException();

        layout = vertices[0].layout();
        vertexCount = vertices.length;

        int size = layout.size() * vertexCount;

        ByteBuffer buffer = MemoryUtil.memAlloc(size);
        for (Vertex v: vertices) {
            ByteBuffer vertexData = v.data();
            buffer.put(vertexData);
            MemoryUtil.memFree(vertexData);
        }

        id = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, id);
        glBufferData(GL_ARRAY_BUFFER, buffer.flip(), GL_STATIC_DRAW);

        MemoryUtil.memFree(buffer);
    }

    private static boolean areAllLayoutsEqual(@NotNull Iterator<Vertex> vertices) {
        if (!vertices.hasNext())
            return true;

        VertexLayout layout = vertices.next().layout();

        while (vertices.hasNext())
            if (!layout.equals(vertices.next().layout()))
                return false;

        return true;
    }

    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, id);
    }

    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    @NotNull public VertexLayout layout() {
        return layout;
    }

    public int vertexCount() {
        return vertexCount;
    }
}