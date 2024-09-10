package nuvola;

import nuvola.input.Input;
import nuvola.input.InputQueue;
import nuvola.window.Window;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import java.util.Objects;

public class Nuvola {
    @NotNull private final Window window;
    @NotNull private final InputQueue inputQueue;

    public Nuvola(@NotNull Window window, @NotNull InputQueue inputQueue) {
        this.window = Objects.requireNonNull(window);
        this.inputQueue = Objects.requireNonNull(inputQueue);
    }

    public void run() {
        while (!window.shouldClose()) {
            window.pollEvents();
            window.clearColorBuffer();

            while (!inputQueue.isEmpty()) {
                Input input = inputQueue.remove();

                if (input instanceof Input.KeyInput key) {
                    System.out.println("Button pressed, " + key);
                    if (key.key() == GLFW.GLFW_KEY_ESCAPE)
                        window.setClose();
                }
            }

            window.swapBuffers();
        }

        window.close();
    }
}
