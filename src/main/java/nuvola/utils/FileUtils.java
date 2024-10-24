package nuvola.utils;

import nuvola.exceptions.misc.UnableOpenFileException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public final class FileUtils {
    private FileUtils() { }

    @NotNull public static String fileToString(@NotNull Path filePath) {
        try {
            return Files.readString(Objects.requireNonNull(filePath));
        } catch (IOException e) {
            throw new UnableOpenFileException(filePath.toString());
        }
    }

    @NotNull public static String fileToString(@NotNull String filePathString) {
        return fileToString(Path.of(Objects.requireNonNull(filePathString)));
    }
}
