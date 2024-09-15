package nuvola.render.shader;

import org.jetbrains.annotations.NotNull;

public class FragmentShader extends Shader {
    public FragmentShader(@NotNull String pathString) {
        super(ShaderType.FRAGMENT_SHADER, pathString);
    }
}