package nuvola.render.shader;

import nuvola.utils.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class VertexShader extends Shader {
    public VertexShader(@NotNull String content) {
        super(ShaderType.VERTEX_SHADER, content);
    }

    @NotNull public static VertexShader fromFile(@NotNull String pathString) {
        String content = FileUtils.fileToString(Objects.requireNonNull(pathString));

        return new VertexShader(content);
    }
}