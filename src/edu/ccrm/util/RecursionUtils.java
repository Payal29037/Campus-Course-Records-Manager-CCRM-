package edu.ccrm.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class RecursionUtils {
    private RecursionUtils() {}

    public static long directorySize(Path dir) throws IOException {
        if (!Files.exists(dir)) return 0L;
        if (Files.isRegularFile(dir)) return Files.size(dir);
        long total = 0L;
        for (Path p : Files.newDirectoryStream(dir)) {
            total += directorySize(p); // recursion
        }
        return total;
    }
}
