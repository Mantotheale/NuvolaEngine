package nuvola.exceptions.vertex;

public class VertexAttributeCountNotPositiveException extends RuntimeException {
    public VertexAttributeCountNotPositiveException(int size) {
        super("The count of a vertex template must be positive, " + size + " is not positive");
    }
}
