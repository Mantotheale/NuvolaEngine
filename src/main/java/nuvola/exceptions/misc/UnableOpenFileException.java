package nuvola.exceptions.misc;

public class UnableOpenFileException extends RuntimeException {
    public UnableOpenFileException(String filepath) {
        super("Couldn't open file " + filepath);
    }
}
