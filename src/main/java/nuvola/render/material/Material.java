package nuvola.render.material;

import nuvola.render.shader.ShaderProgram;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class Material {
    @NotNull protected final ShaderProgram shader;

    public Material(@NotNull ShaderProgram shader) {
        this.shader = Objects.requireNonNull(shader);
    }

    @NotNull public ShaderProgram shader() {
        return shader;
    }

    public abstract void bind();
}