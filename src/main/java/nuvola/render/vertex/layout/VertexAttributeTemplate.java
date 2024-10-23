package nuvola.render.vertex.layout;

import nuvola.exceptions.vertex.VertexAttributeCountNotPositiveException;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record VertexAttributeTemplate(@NotNull OpenGLType openGLType, int count) {
    public VertexAttributeTemplate {
        Objects.requireNonNull(openGLType);

        if (count <= 0)
            throw new VertexAttributeCountNotPositiveException(count);
    }

    public int size() {
        return count * openGLType.size();
    }

    @Override
    public String toString() {
        return "{type: " + openGLType + ", count: " + count + ", size: " + size() + "}";
    }

    @NotNull public static VertexAttributeTemplate ThreeDimensionsPosition = new VertexAttributeTemplate(OpenGLType.FLOAT, 3);
    @NotNull public static VertexAttributeTemplate ThreeChannelsColor = new VertexAttributeTemplate(OpenGLType.FLOAT, 3);
    @NotNull public static VertexAttributeTemplate FourChannelsColor= new VertexAttributeTemplate(OpenGLType.FLOAT, 4);
    @NotNull public static VertexAttributeTemplate TwoDimensionsTextureCoordinates = new VertexAttributeTemplate(OpenGLType.FLOAT, 2);
}