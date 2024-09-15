package nuvola.exceptions.vertex;

public class VertexValuesNullException extends RuntimeException {
    public VertexValuesNullException() {
        super("The values in a vertex can't be null");
    }
}
