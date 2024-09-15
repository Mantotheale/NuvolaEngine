package nuvola.render.shader;

import org.jetbrains.annotations.NotNull;

public class VertexShader extends Shader {
    public VertexShader(@NotNull String pathString) {
        super(ShaderType.VERTEX_SHADER, pathString);
    }
}