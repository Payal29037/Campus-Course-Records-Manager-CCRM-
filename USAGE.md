# Usage

Compile and run (from project root):

```powershell
javac -d out -sourcepath src src/edu/ccrm/cli/Main.java
java -cp out edu.ccrm.cli.Main
```

Sample test data files are under `test-data/`.

## Menu Overview
- `1` Manage Students (coming soon)
- `2` Manage Courses (coming soon)
- `3` Enrollment & Grades (coming soon)
- `4` Import/Export Data (demo)
  - Imports `test-data/students.csv` and `test-data/courses.csv` and prints record counts
  - Exports demo data to `exports/`
- `5` Backup & Directory Size (demo)
  - Prints sizes for `data/`, `exports/`, and `backups/`
- `6` Reports (coming soon)
- `h` or `?` Help
- `0` Exit

## Windows Tips
- If console colors look odd, disable ANSI colors:
  ```powershell
  $env:CCRM_ANSI = "0"
  java -cp out edu.ccrm.cli.Main
  ```

## Build and Run as JAR (optional)
```powershell
del out\*.class -ErrorAction SilentlyContinue
javac -d out -sourcepath src src/edu/ccrm/cli/Main.java
echo Main-Class: edu.ccrm.cli.Main > manifest.mf
jar cfm ccrm.jar manifest.mf -C out .
java -jar ccrm.jar
```
