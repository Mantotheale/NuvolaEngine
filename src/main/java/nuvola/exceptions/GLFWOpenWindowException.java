package nuvola.exceptions;

public class GLFWOpenWindowException extends RuntimeException {
    public GLFWOpenWindowException() {
        super("Failed to open a window");
    }
}
