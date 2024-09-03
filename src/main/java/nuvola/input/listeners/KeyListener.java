package nuvola.input.listeners;

import nuvola.input.Input;
import nuvola.input.InputListener;
import nuvola.window.Window;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class KeyListener extends InputListener {
    public KeyListener(@NotNull Window window) {
        Objects.requireNonNull(window).setKeyCallback(this::keyPressed);
    }

    public void keyPressed(long window, int key, int scancode, int action, int mods) {
        notifyObservers(new Input.KeyInput(key, action, mods));
    }
}
