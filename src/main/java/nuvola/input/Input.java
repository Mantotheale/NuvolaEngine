package nuvola.input;

public sealed interface Input {
    record NoInput() implements Input { }

    record ClickInput(int button, int action, int modifier) implements Input { }

    record KeyInput(int key, int action, int modifier) implements Input { }

    record MouseMovementInput(double xPos, double yPos) implements Input { }

    record ResizeWindowInput(int width, int height) implements Input { }

    record ScrollInput(double vertical) implements Input { }
}
