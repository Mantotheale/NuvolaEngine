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
import nuvola.render.vertex.*;
import nuvola.render.vertex.attribute.Position3DAttribute;
import nuvola.render.vertex.attribute.TexCoords2DAttribute;
import nuvola.window.Window;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import java.nio.file.Path;
import java.util.List;
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
        Path shaderDirectory = Path.of("src/main/resources/shaders/");
        Path vertexShaderPath = shaderDirectory.resolve("vertexShaders/textureVertexShader.vert");
        Path fragmentShaderPath = shaderDirectory.resolve("fragmentShaders/textureFragmentShader.frag");

        ShaderProgram shaderProgram = new ShaderProgram(vertexShaderPath, fragmentShaderPath);

        Texture texture = new Texture("src/main/resources/textures/suricati.jpg");

        Material material = new SimpleTextureMaterial(shaderProgram, texture);

        VertexBuffer vertexBuffer = new VertexBuffer(
            new Vertex(new Position3DAttribute(-0.5f, -0.5f, 0.5f), new TexCoords2DAttribute(0, 0)),
            new Vertex(new Position3DAttribute(0.5f, -0.5f, 0), new TexCoords2DAttribute(1, 0)),
            new Vertex(new Position3DAttribute(0.5f, 0.5f, 0), new TexCoords2DAttribute(1, 1)),
            new Vertex(new Position3DAttribute(-0.5f, 0.5f, 0), new TexCoords2DAttribute(0, 1))
            );

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
