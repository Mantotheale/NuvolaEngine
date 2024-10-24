package nuvola.exceptions.vertex;

public class VertexBufferNotHomogenousException extends RuntimeException {
    public VertexBufferNotHomogenousException() {
        super("The vertices in a vertex buffer should be all of the same type");
    }
}
