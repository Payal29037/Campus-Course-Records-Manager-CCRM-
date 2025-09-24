/*
  * Project: Campus Course & Records Manager (CCRM)
  * File: FileSystemBackupService.java
  * Author: Payal Kushwaha
  * Created: 2025-09-25
  * Version: v0.1.0
  * Notes: Implements timestamped backups under AppConfig.backupDir and computes sizes.
  */
package edu.ccrm.io.impl;

import edu.ccrm.config.AppConfig;
import edu.ccrm.io.BackupService;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * File-system based backup implementation.
 * Copies the given directory into a timestamped subdirectory under AppConfig.backupDir.
 * Also exposes a readable recursive size computation as part of the interface.
 */
public class FileSystemBackupService implements BackupService {

    @Override
    public Path backupNow(Path fromDir) throws IOException {
        if (fromDir == null) throw new IllegalArgumentException("Source directory can't be null");
        if (!Files.exists(fromDir)) throw new NoSuchFileException("Source does not exist: " + fromDir);

        AppConfig config = AppConfig.getInstance();
        Path targetRoot = config.timestampedBackupSubdir();
        Files.createDirectories(targetRoot);

        // Copy the source directory tree into targetRoot/<sourceFolderName>
        Path target = targetRoot.resolve(fromDir.getFileName());
        copyRecursive(fromDir, target);
        return target;
    }

    @Override
    public long computeRecursiveSize(Path dir) throws IOException {
        if (dir == null || !Files.exists(dir)) return 0L;
        final long[] total = {0L};
        Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                total[0] += Files.size(file);
                return FileVisitResult.CONTINUE;
            }
        });
        return total[0];
    }

    private static void copyRecursive(Path source, Path target) throws IOException {
        Files.walkFileTree(source, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path rel = source.relativize(dir);
                Path dest = target.resolve(rel);
                Files.createDirectories(dest);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Path rel = source.relativize(file);
                Path dest = target.resolve(rel);
                Files.copy(file, dest, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
