package nuvola.render;

import nuvola.render.shader.ShaderProgram;
import org.jetbrains.annotations.NotNull;

public class Renderer {
    public void render(@NotNull Mesh mesh) {
        mesh.bind();
        mesh.draw();
    }

    public void render(@NotNull RenderComponent renderComponent) {

    }
}