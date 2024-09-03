package nuvola.exceptions;

public class GLFWInitializationException extends RuntimeException {
    public GLFWInitializationException() {
        super("Couldn't initialize GLFW");
    }
}
