package nuvola;

import nuvola.window.Window;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Nuvola {
    @NotNull private final Window window;

    public Nuvola(@NotNull Window window) {
        this.window = Objects.requireNonNull(window);
    }

    public void run() {
        while (!window.shouldClose()) {
            window.pollEvents();
            window.clearColorBuffer();

            window.swapBuffers();
        }

        window.close();
    }
}
