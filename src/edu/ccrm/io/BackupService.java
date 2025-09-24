package edu.ccrm.io;

import java.io.IOException;
import java.nio.file.Path;

public interface BackupService {
    Path backupNow(Path fromDir) throws IOException;
    long computeRecursiveSize(Path dir) throws IOException; // recursion demonstration
}
