package nuvola.utils;

import nuvola.exceptions.misc.UnableOpenFileException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public final class FileUtils {
    private FileUtils() { }

    @NotNull public static String fileToString(@NotNull String filePathString) {
        Path filePath = Path.of(Objects.requireNonNull(filePathString));

        try {
            return Files.readString(filePath);
        } catch (IOException e) {
            throw new UnableOpenFileException(filePathString);
        }
    }
}
