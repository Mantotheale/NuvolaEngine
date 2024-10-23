package nuvola.render;

import nuvola.render.material.Material;
import nuvola.render.shader.ShaderProgram;
import nuvola.render.vertex.VertexArray;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static org.lwjgl.opengl.GL11.*;

public class Mesh {
    @NotNull private final VertexArray vertexArray;
    @NotNull private final Material material;

    public Mesh(@NotNull VertexArray vertexArray, @NotNull Material material) {
        this.vertexArray = Objects.requireNonNull(vertexArray);
        this.material = Objects.requireNonNull(material);
    }

    @NotNull public ShaderProgram shader() {
        return material.shader();
    }

    public void bind() {
        vertexArray.bind();
        material.bind();
    }

    public void draw() {
        if (vertexArray.isIndexSet())
            glDrawElements(GL_TRIANGLES, vertexArray.indexCount(), GL_UNSIGNED_INT, 0);
        else
            glDrawArrays(GL_TRIANGLES, 0, vertexArray.count());
    }
}
