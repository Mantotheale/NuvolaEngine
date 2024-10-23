package nuvola.render.shader;

import nuvola.exceptions.shader.ShaderProgramLinkingException;
import nuvola.exceptions.shader.ShaderUniformNotFoundException;
import nuvola.render.material.Texture;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;

public class ShaderProgram {
    private final int id;
    @NotNull private final String content;

    public ShaderProgram(@NotNull VertexShader vertexShader, @NotNull FragmentShader fragmentShader) {
        id = glCreateProgram();

        glAttachShader(id, vertexShader.id());
        glAttachShader(id, fragmentShader.id());
        content = "VERTEX SHADER:\n" + vertexShader + "\n\nFRAGMENT SHADER:\n" + fragmentShader;

        glLinkProgram(id);

        if(glGetProgrami(id, GL_LINK_STATUS) == GL_FALSE)
            throw new ShaderProgramLinkingException(this, glGetProgramInfoLog(id));

        glDetachShader(id, vertexShader.id());
        glDetachShader(id, fragmentShader.id());
    }

    public void bind() {
        glUseProgram(id);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void delete() {
        glDeleteProgram(id);
    }

    public void setUniform(String name, boolean value) {
        bind();
        glUniform1i(getUniformLocation(name), Boolean.compare(value, false));
    }

    public void setUniform(String name, int value) {
        bind();
        glUniform1i(getUniformLocation(name), value);
    }

    public void setUniform(String name, float value) {
        bind();
        glUniform1f(getUniformLocation(name), value);
    }

    public void setUniform(String name, float x, float y, float z) {
        bind();
        glUniform3f(getUniformLocation(name), x, y, z);
    }

    public void setUniform(String name, @NotNull Vector3f value) {
        bind();
        glUniform3f(getUniformLocation(name), value.x, value.y, value.z);
    }

    public void setUniform(String name, @NotNull Texture value) {
        bind();
        glUniform1i(getUniformLocation(name), value.id());
    }

    public void setUniform(String name, @NotNull Matrix4f value) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer =  value.get(stack.mallocFloat(16));

            bind();
            glUniformMatrix4fv(getUniformLocation(name), false, buffer);
        }
    }

    private int getUniformLocation(String name) {
        int location = glGetUniformLocation(id, name);

        if (location == -1)
            throw new ShaderUniformNotFoundException(this, name);

        return location;
    }

    @Override
    public String toString() {
        return "id: " + id + "\ncontent:\n" + content;
    }
}
