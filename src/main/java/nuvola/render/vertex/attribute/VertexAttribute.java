package nuvola.render.vertex.attribute;

import nuvola.render.vertex.layout.VertexAttributeTemplate;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

public interface VertexAttribute {
    @NotNull VertexAttributeTemplate layout();
    @NotNull ByteBuffer data();
}