package nuvola.render.shader;

import nuvola.utils.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public class VertexShader extends Shader {
    public VertexShader(@NotNull String content) {
        super(ShaderType.VERTEX_SHADER, content);
    }

    @NotNull public static VertexShader fromFile(@NotNull Path path) {
        return new VertexShader(FileUtils.fileToString(path));
    }

    @NotNull public static VertexShader fromFile(@NotNull String pathString) {
        return new VertexShader(FileUtils.fileToString(pathString));
    }
}