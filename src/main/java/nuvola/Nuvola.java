package nuvola;

import nuvola.input.Input;
import nuvola.input.InputQueue;
import nuvola.render.Mesh;
import nuvola.render.Renderer;
import nuvola.render.material.Material;
import nuvola.render.material.SimpleTextureMaterial;
import nuvola.render.shader.FragmentShader;
import nuvola.render.shader.ShaderProgram;
import nuvola.render.shader.VertexShader;
import nuvola.render.material.Texture;
import nuvola.render.vertex.IndexBuffer;
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

    Mesh mesh;
    Renderer renderer;

    public Nuvola(@NotNull Window window, @NotNull InputQueue inputQueue) {
        this.window = Objects.requireNonNull(window);
        this.inputQueue = Objects.requireNonNull(inputQueue);

        setup();
    }

    private void setup() {
        String shaderDirectory = "src/main/resources/shaders/";
        VertexShader vShader = VertexShader.fromFile(shaderDirectory + "/vertexShaders/textureVertexShader.vert");
        FragmentShader fShader = FragmentShader.fromFile(shaderDirectory + "/fragmentShaders/textureFragmentShader.frag");

        ShaderProgram shaderProgram = new ShaderProgram(vShader, fShader);

        vShader.delete();
        fShader.delete();

        Texture texture = new Texture("src/main/resources/textures/suricati.jpg");

        Material material = new SimpleTextureMaterial(shaderProgram, texture);

        VertexLayout layout = new VertexLayout.Builder()
            .addTemplate(VertexAttributeTemplate.ThreeDimensionsPosition)
            .addTemplate(VertexAttributeTemplate.TwoDimensionsTextureCoordinates)
            .build();

        VertexBuffer vertexBuffer = new VertexBuffer(layout,
            -0.5f, -0.5f, 0f, 0f, 0f,
            0.5f, -0.5f, 0f, 1f, 0f,
            0.5f, 0.5f, 0f, 1f, 1f,
            -0.5f, 0.5f, 0f, 0f, 1f);

        IndexBuffer indexBuffer = new IndexBuffer(
            0, 1, 2,
                0, 2, 3
        );

        VertexArray vertexArray = new VertexArray(vertexBuffer, indexBuffer);
        mesh = new Mesh(vertexArray, material);
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
                        window.signalClose();
                }
            }

            renderer.render(mesh);

            window.swapBuffers();
        }

        window.close();
    }
}
