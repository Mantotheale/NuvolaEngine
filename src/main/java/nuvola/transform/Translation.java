package nuvola.transform;

import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public record Translation(float x, float y, float z) implements SpacialTransformation {
    public Translation(@NotNull Vector3f translation) {
        this(translation.x, translation.y, translation.z);
    }

    public @NotNull Matrix4f matrix() {
        return new Matrix4f().translation(x, y, z);
    }

    @Override
    public @NotNull Point apply(@NotNull Point point) {
        return new Point(matrix().transform(point.vector()));
    }

    @NotNull public static Translation IDENTITY = new Translation(0, 0, 0);
}
