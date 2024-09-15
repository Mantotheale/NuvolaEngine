package nuvola.exceptions.vertex;

public class VertexAttributeListIsEmptyException extends RuntimeException {
    public VertexAttributeListIsEmptyException() {
        super("A vertex template list in a layout must not be empty");
    }
}
