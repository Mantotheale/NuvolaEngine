package nuvola.transform;

import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

public interface SpacialTransformation {
    @NotNull Point apply(@NotNull Point point);
    @NotNull Matrix4f matrix();
}
