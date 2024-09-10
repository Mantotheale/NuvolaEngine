package nuvola.transform;

import org.jetbrains.annotations.NotNull;
import org.joml.AxisAngle4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.Objects;

public record Rotation(@NotNull Quaternionf quaternion) implements SpacialTransformation {
    public Rotation {
        Objects.requireNonNull(quaternion);
    }

    public Rotation(@NotNull Vector3f axis, float angle) {
        this(new Quaternionf(new AxisAngle4f(angle, axis)));
    }

    @Override
    public @NotNull Point apply(@NotNull Point point) {
        return new Point(quaternion.transform(point.vector()));
    }

    @NotNull public static Rotation IDENTITY = new Rotation(new Quaternionf());
}

