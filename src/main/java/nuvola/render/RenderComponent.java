package nuvola.render;

import nuvola.render.shader.ShaderProgram;
import nuvola.transform.Transform;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record RenderComponent(@NotNull Mesh mesh, @NotNull Transform transform) {
    public RenderComponent {
        Objects.requireNonNull(mesh);
        Objects.requireNonNull(transform);
    }

    @NotNull public ShaderProgram shader() {
        return mesh.shader();
    }
}
