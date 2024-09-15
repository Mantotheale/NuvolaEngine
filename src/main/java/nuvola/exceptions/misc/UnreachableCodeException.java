package nuvola.exceptions.misc;

public class UnreachableCodeException extends RuntimeException {
    public UnreachableCodeException() {
        super("Shouldn't arrive here");
    }
}
