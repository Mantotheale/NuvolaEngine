package nuvola.render.vertex;

import nuvola.exceptions.vertex.UnexpectedTypeInsideVertexException;
import nuvola.exceptions.misc.UnreachableCodeException;
import nuvola.exceptions.vertex.VertexValuesNullException;
import nuvola.render.vertex.layout.VertexLayout;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;
import java.util.Objects;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;

public class VertexBuffer {
    private final int id;
    private final VertexLayout layout;
    private final int vertexCount;

    public VertexBuffer(@NotNull VertexLayout layout, Number ...values) {
        this.layout = Objects.requireNonNull(layout);

        int size = calculateSize(values);
        vertexCount = size / layout.size();

        ByteBuffer buffer = loadBuffer(size, values);

        id = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, id);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);

        MemoryUtil.memFree(buffer);
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

    private static int calculateSize(@NotNull Number[] values) {
        int size = 0;

        for (Number n: values) {
            switch (n) {
                case Integer x -> size += Integer.BYTES;
                case Float x -> size += Float.BYTES;
                case Byte x -> size += Byte.BYTES;
                case null -> throw new VertexValuesNullException();
                case Object x -> throw new UnexpectedTypeInsideVertexException(x);
            }
        }

        return size;
    }

    @NotNull private static ByteBuffer loadBuffer(int size, @NotNull Number[] values) {
        ByteBuffer buffer = MemoryUtil.memAlloc(size);

        for (Number n: values) {
            switch (n) {
                case Integer x -> buffer.asIntBuffer().put(x);
                case Float x -> buffer.asFloatBuffer().put(x);
                case Byte x -> buffer.put(x);
                default -> throw new UnreachableCodeException();
            }
        }

        return buffer.flip();
    }
}