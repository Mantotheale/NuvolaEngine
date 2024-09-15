package nuvola.exceptions.vertex;

public class UnexpectedTypeInsideVertexException extends RuntimeException {
    public UnexpectedTypeInsideVertexException(Object obj) {
        super("Type " + obj.getClass() + " is not allowed inside a vertex");
    }
}
