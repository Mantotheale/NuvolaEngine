package nuvola.transform;

import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public record Scale(float x, float y, float z) implements SpacialTransformation {
    public Scale(@NotNull Vector3f scale) {
        this(scale.x, scale.y, scale.z);
    }

    private @NotNull Matrix4f transformationMatrix() {
        return new Matrix4f().scaling(x, y, z);
    }

    @Override
    public @NotNull Point apply(@NotNull Point point) {
        return new Point(transformationMatrix().transform(point.vector()));
    }

    @NotNull public static Scale IDENTITY = new Scale(1, 1, 1);
}
