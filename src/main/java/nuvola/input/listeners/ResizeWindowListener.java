package nuvola.input.listeners;

import nuvola.input.Input;
import nuvola.input.InputListener;
import nuvola.window.Window;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ResizeWindowListener extends InputListener {

    public ResizeWindowListener(@NotNull Window window) {
        Objects.requireNonNull(window).setWindowSizeCallback(this::resizeWindow);
    }

    public void resizeWindow(long window, int width, int height) {
        notifyObservers(new Input.ResizeWindowInput(width, height));
    }
}
