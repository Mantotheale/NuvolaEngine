package nuvola.exceptions.texture;

public class UnsupportedChannelsNumberException extends RuntimeException {
    public UnsupportedChannelsNumberException() {
        super("Only images with 3 or 4 channels are supported");
    }
}
