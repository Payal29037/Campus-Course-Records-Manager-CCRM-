package edu.ccrm.io.impl;

import edu.ccrm.cli.Console;
import edu.ccrm.io.ImportExportService;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Minimal Import/Export implementation that reads CSVs and reports counts.
 * This is intentionally simple to keep the demo user-friendly.
 */
public class SimpleImportExportService implements ImportExportService {

    @Override
    public void importStudents(Path file) {
        long count = countCsvRecords(file);
        Console.println(Console.green("Imported students: " + count + " record(s) from " + file));
    }

    @Override
    public void importCourses(Path file) {
        long count = countCsvRecords(file);
        Console.println(Console.green("Imported courses: " + count + " record(s) from " + file));
    }

    @Override
    public void exportAll(Path outputDir) {
        try {
            Files.createDirectories(outputDir);
            // Demo: in a real app we'd write CSVs here
            Console.println(Console.cyan("Exported demo data to: " + outputDir.toAbsolutePath()));
        } catch (IOException e) {
            Console.println(Console.red("Failed to create export directory: " + e.getMessage()));
        }
    }

    private long countCsvRecords(Path file) {
        if (file == null) {
            Console.println(Console.red("CSV file path is null"));
            return 0L;
        }
        if (!Files.exists(file)) {
            Console.println(Console.yellow("CSV file not found: " + file.toAbsolutePath()));
            return 0L;
        }
        try (BufferedReader br = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            boolean headerSkipped = false;
            long count = 0L;
            while ((line = br.readLine()) != null) {
                if (!headerSkipped) { headerSkipped = true; continue; }
                if (line.trim().isEmpty()) continue;
                count++;
            }
            return count;
        } catch (IOException e) {
            Console.println(Console.red("Failed reading CSV: " + e.getMessage()));
            return 0L;
        }
    }
}
