package nuvola.transform;

import org.jetbrains.annotations.NotNull;
import org.joml.Vector4f;

public record Point(float x, float y, float z, float w) {
    public Point(@NotNull Vector4f point) {
        this(point.x, point.y, point.z, point.w);
    }

    public @NotNull Vector4f vector() {
        return new Vector4f(x, y, z, w);
    }
}
