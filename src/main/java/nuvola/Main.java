package nuvola;

import nuvola.input.InputQueue;
import nuvola.input.listeners.KeyListener;
import nuvola.transform.*;
import nuvola.window.Window;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Window window = new Window("prova", 1080, 720);

        KeyListener keyListener = new KeyListener(window);
        InputQueue inputQueue = new InputQueue(List.of(keyListener));

        Nuvola nuvola = new Nuvola(window, inputQueue);

        Transform transform = new Transform(
            new Translation(1, 2, 3),
            new Rotation(new Vector3f(0, 0, 1), (float) Math.toRadians(90)),
            new Scale(3, 2, 1)
        );

        Point p = new Point(1, 0, 0, 1);

        System.out.println(transform.apply(p));

        nuvola.run();
    }
}