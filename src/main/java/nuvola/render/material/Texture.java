package nuvola.render.material;

import nuvola.exceptions.misc.UnableOpenFileException;
import nuvola.exceptions.texture.UnsupportedChannelsNumberException;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;
import static org.lwjgl.stb.STBImage.*;

public class Texture {
    private final int id;
    private final int width;
    private final int height;
    private int boundSlot = -1;

    public Texture(@NotNull String imagePathString) {
        id = glGenTextures();

        glBindTexture(GL_TEXTURE_2D, id);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer width = stack.mallocInt(1);
            IntBuffer height = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            stbi_set_flip_vertically_on_load(true);
            ByteBuffer image = stbi_load(imagePathString, width, height, channels, 0);

            if (image == null)
                throw new UnableOpenFileException(imagePathString);

            int channelType = switch (channels.get(0)) {
                case 3 -> GL_RGB;
                case 4 -> GL_RGBA;
                default -> throw new UnsupportedChannelsNumberException();
            };

            this.width = width.get(0);
            this.height = height.get(0);

            glTexImage2D(GL_TEXTURE_2D, 0, channelType, width.get(0), height.get(0), 0, channelType, GL_UNSIGNED_BYTE, image);
            glGenerateMipmap(GL_TEXTURE_2D);

            stbi_image_free(image);
        }

        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public Texture(int width, int height) {
        id = glGenTextures();
        this.width = width;
        this.height = height;

        glBindTexture(GL_TEXTURE_2D, id);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, 0);

        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void bind(int slot) {
        glActiveTexture(GL_TEXTURE0 + slot);
        glBindTexture(GL_TEXTURE_2D, id);
        boundSlot = slot;
    }

    public void bind() {
        bind(0);
    }

    public void unbind() {
        glActiveTexture(boundSlot);
        glBindTexture(GL_TEXTURE_2D, 0);
        boundSlot = -1;
    }

    public boolean isBound() {
        return boundSlot != -1;
    }

    public int id() { return id; }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }
}
