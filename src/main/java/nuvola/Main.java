package nuvola;

import nuvola.window.Window;

public class Main {
    public static void main(String[] args) {
        Window window = new Window("prova", 1080, 720);
        Nuvola nuvola = new Nuvola(window);

        nuvola.run();

        System.out.println("Hello world!");
    }
}