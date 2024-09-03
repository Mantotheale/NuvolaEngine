package nuvola;

import nuvola.window.Window;

public class Main {
    public static void main(String[] args) {
        Window window = new Window("prova", 1080, 720);

        window.setClearColor(0.5f, 0.5f, 0.5f);
        while (!window.shouldClose()) {
            window.pollEvents();
            window.clearColorBuffer();

            window.swapBuffers();
        }

        window.close();

        System.out.println("Hello world!");
    }
}