package nuvola.exceptions.glfw;

public class GLFWOpenWindowException extends RuntimeException {
    public GLFWOpenWindowException() {
        super("Failed to open a window");
    }
}
