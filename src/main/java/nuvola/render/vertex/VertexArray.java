package nuvola.render.vertex;

import nuvola.render.vertex.layout.VertexAttributeTemplate;
import org.jetbrains.annotations.NotNull;

import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class VertexArray {
    private final int id;
    private VertexBuffer vertexBuffer;
    private IndexBuffer indexBuffer;

    public VertexArray() {
        id = glGenVertexArrays();
    }

    public VertexArray(@NotNull VertexBuffer vertexBuffer) {
        this();
        setVertexBuffer(vertexBuffer);
    }

    public VertexArray(@NotNull VertexBuffer vertexBuffer, @NotNull IndexBuffer indexBuffer) {
        this(vertexBuffer);
        setIndexBuffer(indexBuffer);
    }

    public int id() {
        return id;
    }

    public void setVertexBuffer(@NotNull VertexBuffer vertexBuffer) {
        glBindVertexArray(id);
        vertexBuffer.bind();

        int i = 0;
        int offset = 0;
        for (VertexAttributeTemplate attributeLayout: vertexBuffer.layout()) {
            glVertexAttribPointer(
                i,
                attributeLayout.count(),
                attributeLayout.type().glEnum(),
                false,
                vertexBuffer.layout().size(),
                offset);
            glEnableVertexAttribArray(i);
            i++;
            offset += attributeLayout.size();
        }

        this.vertexBuffer = vertexBuffer;
    }

    public void removeVertexBuffer() {
        if (isVertexBufferSet()) {
            glBindVertexArray(id);
            vertexBuffer.unbind();
            vertexBuffer = null;
        }
    }

    public void setIndexBuffer(@NotNull IndexBuffer indexBuffer) {
        glBindVertexArray(id);
        indexBuffer.bind();

        this.indexBuffer = indexBuffer;
    }

    public void removeIndexBuffer() {
        if (isIndexSet()) {
            glBindVertexArray(id);
            indexBuffer.unbind();
            indexBuffer = null;
        }
    }

    public void bind() {
        glBindVertexArray(id);
    }

    public void unbind() {
        glBindVertexArray(0);
    }

    public int vertexCount() {
        return isVertexBufferSet() ? vertexBuffer.vertexCount() : 0;
    }

    public int indexCount() {
        return isIndexSet() ? indexBuffer.count() : 0;
    }

    public boolean isVertexBufferSet() {
        return vertexBuffer != null;
    }

    public boolean isIndexSet() {
        return indexBuffer != null;
    }
}
