package nuvola.input.listeners;

import nuvola.input.Input;
import nuvola.input.InputListener;
import nuvola.window.Window;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ScrollListener extends InputListener {
    public ScrollListener(@NotNull Window window) {
         Objects.requireNonNull(window).setScrollCallback(this::mouseScrolled);
    }

    public void mouseScrolled(long window, double xOffset, double yOffset) {
        notifyObservers(new Input.ScrollInput(yOffset));
    }
}
