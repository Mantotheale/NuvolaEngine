package nuvola.input;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class InputQueue extends InputObserver implements Iterable<Input> {
    @NotNull private final Queue<Input> inputs = new ArrayDeque<>();

    public InputQueue(@NotNull List<InputListener> listeners) {
        for (InputListener l: listeners)
            l.addObserver(this);
    }

    @Override
    public void inputOccurred(@NotNull Input input) {
        inputs.add(Objects.requireNonNull(input));
    }

    @NotNull public Input remove() {
        return inputs.remove();
    }

    public boolean isEmpty() {
        return inputs.isEmpty();
    }

    public void clear() {
        inputs.clear();
    }

    @Override
    @NotNull public Iterator<Input> iterator() {
        return inputs.stream().toList().iterator();
    }
}
