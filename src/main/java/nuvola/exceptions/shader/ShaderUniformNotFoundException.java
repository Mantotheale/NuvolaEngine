package nuvola.exceptions.shader;

import nuvola.render.shader.ShaderProgram;

public class ShaderUniformNotFoundException extends RuntimeException {
    public ShaderUniformNotFoundException(ShaderProgram shaderProgram, String uniformName) {
        super("An uniform with name " + uniformName + " doesn't exist in shader:\n" + shaderProgram);
    }
}
