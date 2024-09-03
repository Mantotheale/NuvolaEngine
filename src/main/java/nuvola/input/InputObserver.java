package nuvola.input;

import org.jetbrains.annotations.NotNull;

public abstract class InputObserver {
    public abstract void inputOccurred(@NotNull Input input);
}
