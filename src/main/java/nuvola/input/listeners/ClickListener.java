package nuvola.input.listeners;

import nuvola.input.Input;
import nuvola.input.InputListener;
import nuvola.window.Window;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ClickListener extends InputListener {
    public ClickListener(@NotNull Window window) {
        Objects.requireNonNull(window).setClickCallback(this::buttonPressed);
    }

    public void buttonPressed(long window, int key, int action, int mods) {
        notifyObservers(new Input.ClickInput(key, action, mods));
    }
}