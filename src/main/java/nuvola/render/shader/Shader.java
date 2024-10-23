package nuvola.render.shader;

import nuvola.exceptions.shader.ShaderCompilationException;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public abstract class Shader {
    private final int id;
    @NotNull private final ShaderType type;
    @NotNull private final String content;
    private boolean isDeleted;

    public Shader(@NotNull ShaderType type, @NotNull String content) {
        this.type = Objects.requireNonNull(type);
        this.content = content;

        id = glCreateShader(type.glType());
        glShaderSource(id, Objects.requireNonNull(content));
        glCompileShader(id);

        if (glGetShaderi(id, GL_COMPILE_STATUS) == GL_FALSE)
            throw new ShaderCompilationException(this, glGetShaderInfoLog(id));
        isDeleted = false;
    }
    public int id() {
        return id;
    }

    public void delete() {
        glDeleteShader(id);
        isDeleted = true;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    @NotNull public ShaderType type() {
        return type;
    }

    @Override
    public String toString() {
        return "id: " + id + "\ncontent:\n" + content;
    }
}
