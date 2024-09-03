package nuvola.input.listeners;

import nuvola.input.Input;
import nuvola.input.InputListener;
import nuvola.window.Window;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MouseListener extends InputListener {
    private final int windowHeight;

    public MouseListener(@NotNull Window window) {
        this.windowHeight = Objects.requireNonNull(window).height();
        window.setCursorPosCallback(this::mouseMoved);
    }

    public void mouseMoved(long window, double xPos, double yPos) {
        notifyObservers(new Input.MouseMovementInput(xPos, windowHeight - yPos - 1));
    }
}
