package nuvola.render.shader;

import nuvola.utils.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class FragmentShader extends Shader {
    public FragmentShader(@NotNull String pathString) {
        super(ShaderType.FRAGMENT_SHADER, pathString);
    }

    @NotNull public static FragmentShader fromFile(@NotNull String pathString) {
        String content = FileUtils.fileToString(Objects.requireNonNull(pathString));

        return new FragmentShader(content);
    }
}