package nuvola.render.material;

import nuvola.render.shader.ShaderProgram;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SimpleTextureMaterial extends Material {
    @NotNull private final Texture texture;

    public SimpleTextureMaterial(@NotNull ShaderProgram shaderProgram, @NotNull Texture texture) {
        super(shaderProgram);
        this.texture = Objects.requireNonNull(texture);
    }

    @Override
    public void bind() {
        shader.setUniform("u_texture", 0);
        texture.bind(0);
    }
}
