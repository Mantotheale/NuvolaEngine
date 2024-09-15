package nuvola.exceptions.shader;

import nuvola.render.shader.ShaderProgram;

public class ShaderProgramLinkingException extends RuntimeException {
    public ShaderProgramLinkingException(ShaderProgram shaderProgram, String infoLog) {
        super("Couldn't link shader program:\n" + shaderProgram + "\nError: " + infoLog);
    }

    public ShaderProgramLinkingException(ShaderProgram shaderProgram) {
        super("Couldn't link shader program:\n" + shaderProgram);
    }

    public ShaderProgramLinkingException(String infoLog) {
        super("Couldn't link shader program, error: " + infoLog);
    }
}
