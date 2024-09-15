package nuvola.exceptions.vertex;

public class OffsetInLayoutNegativeException extends IllegalArgumentException {
    public OffsetInLayoutNegativeException(int offset) {
        super("Offset in a vertex layout must not be negative, " + offset + " is invalid");
    }
}
