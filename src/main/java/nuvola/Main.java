package nuvola;

import nuvola.input.InputQueue;
import nuvola.input.listeners.KeyListener;
import nuvola.window.Window;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Window window = new Window("prova", 1080, 720);

        KeyListener keyListener = new KeyListener(window);
        InputQueue inputQueue = new InputQueue(List.of(keyListener));

        Nuvola nuvola = new Nuvola(window, inputQueue);



        nuvola.run();

        System.out.println("Hello world!");
    }
}