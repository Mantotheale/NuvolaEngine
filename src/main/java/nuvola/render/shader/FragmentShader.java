package nuvola.render.shader;

import nuvola.utils.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public class FragmentShader extends Shader {
    public FragmentShader(@NotNull String pathString) {
        super(ShaderType.FRAGMENT_SHADER, pathString);
    }

    @NotNull public static FragmentShader fromFile(@NotNull Path path) {
        return new FragmentShader(FileUtils.fileToString(path));
    }

    @NotNull public static FragmentShader fromFile(@NotNull String pathString) {
        return new FragmentShader(FileUtils.fileToString(pathString));
    }
}