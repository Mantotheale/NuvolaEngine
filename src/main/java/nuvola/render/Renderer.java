package nuvola.render;

import nuvola.render.shader.ShaderProgram;
import nuvola.render.vertex.VertexArray;
import org.jetbrains.annotations.NotNull;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {
    public void draw(@NotNull VertexArray vertexArray, @NotNull ShaderProgram shader) {
        vertexArray.bind();
        shader.bind();

        glDrawArrays(GL_TRIANGLES, 0, vertexArray.count());
    }
}
