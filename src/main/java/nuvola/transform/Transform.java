package nuvola.transform;

import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

public class Transform implements SpacialTransformation {
    @NotNull public Translation translation;
    @NotNull public Rotation rotation;
    @NotNull public Scale scale;

    public Transform(@NotNull Translation translation, @NotNull Rotation rotation, @NotNull Scale scale) {
        this.translation = translation;
        this.rotation = rotation;
        this.scale = scale;
    }

    @Override
    public @NotNull Point apply(@NotNull Point point) {
        return rotation.apply(translation.apply(scale.apply(point)));
    }

    @Override
    public @NotNull Matrix4f matrix() {
        return new Matrix4f().mul(scale.matrix()).mul(translation.matrix()).mul(rotation.matrix());
    }
}
