package nuvola.render.material;

import nuvola.render.shader.ShaderProgram;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PhongMaterial extends Material {
    @NotNull private final Texture diffuse;
    @NotNull private final Texture specular;
    private final float shininess;

    public PhongMaterial(@NotNull ShaderProgram shaderProgram, @NotNull Texture diffuse, @NotNull Texture specular, float shininess) {
        super(shaderProgram);
        this.diffuse = Objects.requireNonNull(diffuse);
        this.specular = Objects.requireNonNull(specular);
        this.shininess = shininess;
    }

    @Override
    public void bind() {
        shader.setUniform("material.diffuse", 0);
        shader.setUniform("material.specular", 1);
        shader.setUniform("material.shininess", shininess);

        diffuse.bind(0);
        specular.bind(1);
    }
}
