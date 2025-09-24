package edu.ccrm.io;

import java.nio.file.Path;

public interface ImportExportService {
    void importStudents(Path file);
    void importCourses(Path file);
    void exportAll(Path outputDir);
}
