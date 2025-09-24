/*
  * Project: Campus Course & Records Manager (CCRM)
  * File: AppConfig.java
  * Author: Payal Kushwaha
  * Created: 2025-09-25
  * Version: v0.1.0
  * Notes: Singleton for application directories and timestamped backup subdir.
  */
package edu.ccrm.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Singleton holding basic application paths/config
public final class AppConfig {
    private static volatile AppConfig INSTANCE;

    private final Path dataDir;
    private final Path exportDir;
    private final Path backupDir;

    private AppConfig() {
        String userDir = System.getProperty("user.dir");
        Path base = Paths.get(userDir);
        this.dataDir = base.resolve("data");
        this.exportDir = base.resolve("exports");
        this.backupDir = base.resolve("backups");
        try {
            Files.createDirectories(dataDir);
            Files.createDirectories(exportDir);
            Files.createDirectories(backupDir);
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize application directories", e);
        }
    }

    public static AppConfig getInstance() {
        if (INSTANCE == null) {
            synchronized (AppConfig.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppConfig();
                }
            }
        }
        return INSTANCE;
    }

    public Path getDataDir() { return dataDir; }
    public Path getExportDir() { return exportDir; }
    public Path getBackupDir() { return backupDir; }

    public Path timestampedBackupSubdir() {
        String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        return backupDir.resolve(ts);
    }
}
