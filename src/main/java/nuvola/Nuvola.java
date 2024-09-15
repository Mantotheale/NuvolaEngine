package nuvola;

import nuvola.input.Input;
import nuvola.input.InputQueue;
import nuvola.render.Renderer;
import nuvola.render.shader.FragmentShader;
import nuvola.render.shader.ShaderProgram;
import nuvola.render.shader.VertexShader;
import nuvola.render.vertex.VertexArray;
import nuvola.render.vertex.VertexBuffer;
import nuvola.render.vertex.layout.VertexAttributeTemplate;
import nuvola.render.vertex.layout.VertexLayout;
import nuvola.window.Window;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import java.util.Objects;

public class Nuvola {
    @NotNull private final Window window;
    @NotNull private final InputQueue inputQueue;

    VertexArray vertexArray;
    ShaderProgram shaderProgram;
    Renderer renderer;

    public Nuvola(@NotNull Window window, @NotNull InputQueue inputQueue) {
        this.window = Objects.requireNonNull(window);
        this.inputQueue = Objects.requireNonNull(inputQueue);

        VertexLayout layout = new VertexLayout.Builder()
            .addTemplate(VertexAttributeTemplate.ThreeDimensionsPosition)
            .build();

        VertexBuffer vertexBuffer = new VertexBuffer(layout,
            0.5f, -0.5f, 0f,
            0f, 0.5f, 0f,
            -0.5f, -0.5f, 0f
        );
        vertexArray = new VertexArray(vertexBuffer);

        VertexShader vertexShader = new VertexShader("src/main/resources/shaders/vertexShaders/basicVertexShader.glsl");
        FragmentShader fragmentShader = new FragmentShader("src/main/resources/shaders/fragmentShaders/basicFragmentShader.glsl");
        shaderProgram = new ShaderProgram(vertexShader, fragmentShader);

        renderer = new Renderer();
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

            renderer.draw(vertexArray, shaderProgram);

            window.swapBuffers();
        }

        window.close();
    }
}
