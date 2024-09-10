package nuvola.transform;

import org.jetbrains.annotations.NotNull;

public interface SpacialTransformation {
    @NotNull Point apply(@NotNull Point point);
}
