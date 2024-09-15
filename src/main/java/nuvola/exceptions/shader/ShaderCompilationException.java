package nuvola.exceptions.shader;

import nuvola.render.shader.Shader;

public class ShaderCompilationException extends RuntimeException {
    public ShaderCompilationException(Shader shader, String infoLog) {
        super("Couldn't compile shader " + shader + "\nError: " + infoLog);
    }

    public ShaderCompilationException(Shader shader) {
        super("Couldn't compile shader " + shader);
    }

    public ShaderCompilationException(String infoLog) {
        super("Couldn't compile shader. Error: " + infoLog);
    }
}
