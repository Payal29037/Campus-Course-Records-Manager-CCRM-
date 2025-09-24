package edu.ccrm.cli;

import edu.ccrm.config.AppConfig;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.io.impl.SimpleImportExportService;
import edu.ccrm.io.BackupService;
import edu.ccrm.io.impl.FileSystemBackupService;
import edu.ccrm.util.RecursionUtils;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        // Enable simple platform note
        Console.println(Console.bold("Campus Course & Records Manager (CCRM)"));
        Console.println("Java Platform Note: SE is used here. ME is for mobile/embedded. EE (Jakarta EE) is for enterprise web applications.");

        // Load configuration (Singleton)
        AppConfig config = AppConfig.getInstance();
        Console.println("Data directory: " + config.getDataDir());

        // Basic CLI menu loop (to be expanded)
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        outer: while (running) { // labeled loop demonstration
            Console.println("\n" + Console.bold("=== CCRM Main Menu ==="));
            Console.println("1. Manage Students");
            Console.println("2. Manage Courses");
            Console.println("3. Enrollment & Grades");
            Console.println("4. Import/Export Data (demo)");
            Console.println("5. Backup & Directory Size (demo)");
            Console.println("6. Reports");
            Console.println("h. Help    ?. Help");
            Console.println("0. Exit");
            Console.print(Console.cyan("Choose an option: "));

            String input = sc.nextLine().trim();
            switch (input) {
                case "1":
                    Console.println("Students module (coming soon) - " + LocalDateTime.now());
                    break;
                case "2":
                    Console.println("Courses module (coming soon)");
                    break;
                case "3":
                    Console.println("Enrollment & Grades (coming soon)");
                    break;
                case "4":
                    runImportExportDemo(config);
                    break;
                case "5":
                    showDirectorySizes(config);
                    break;
                case "6":
                    Console.println("Reports (coming soon)");
                    break;
                case "h":
                case "H":
                case "?":
                    printHelp();
                    break;
                case "0":
                    Console.println("Exiting... Goodbye!");
                    break outer; // labeled break example
                default:
                    Console.println(Console.yellow("Invalid choice. Please try again."));
                    continue; // demonstrate continue
            }
        }

        sc.close();
    }

    private static void printHelp() {
        Console.println(Console.bold("Help"));
        Console.println("- Use number keys to select a menu item.");
        Console.println("- Option 4 runs a simple import/export demo using CSVs under test-data/.");
        Console.println("- Option 5 shows sizes of important directories (data, exports, backups).");
        Console.println("- Set env var CCRM_ANSI=0 to disable colors if your terminal shows artifacts.");
    }

    private static void runImportExportDemo(AppConfig config) {
        ImportExportService svc = new SimpleImportExportService();
        Path students = Paths.get("test-data", "students.csv");
        Path courses = Paths.get("test-data", "courses.csv");
        Console.println(Console.bold("\nImport Demo"));
        svc.importStudents(students);
        svc.importCourses(courses);

        Console.println(Console.bold("\nExport Demo"));
        svc.exportAll(config.getExportDir());
    }

    private static void showDirectorySizes(AppConfig config) {
        try {
            // Perform a real backup of the data directory first
            BackupService backupService = new FileSystemBackupService();
            Path createdBackup = backupService.backupNow(config.getDataDir());
            long createdBackupSize = backupService.computeRecursiveSize(createdBackup);

            long data = RecursionUtils.directorySize(config.getDataDir());
            long exports = RecursionUtils.directorySize(config.getExportDir());
            long backups = RecursionUtils.directorySize(config.getBackupDir());
            Console.println(Console.bold("\nDirectory Sizes (bytes)"));
            Console.println("data    : " + data);
            Console.println("exports : " + exports);
            Console.println("backups : " + backups);
            Console.println(Console.cyan("Created backup at: " + createdBackup.toAbsolutePath() + " (" + createdBackupSize + " bytes)"));
        } catch (Exception e) {
            Console.println(Console.red("Failed to compute directory sizes: " + e.getMessage()));
        }
    }
}
