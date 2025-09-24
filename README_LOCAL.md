# Campus Course & Records Manager (CCRM)

CCRM is a beginner-friendly, console-based Java SE application to manage Students, Courses, Enrollments, Grades, Imports/Exports, and Backups. It comes with a simple CLI, colorful prompts, and sample CSVs to get you going fast.

## Features
- Simple interactive menu-driven CLI
- Import demo: counts records from `test-data/students.csv` and `test-data/courses.csv`
- Export demo: creates an `exports/` folder
- Backup/demo utilities: compute directory sizes recursively
- Colorful output with Windows-friendly fallback

## Quickstart (Windows PowerShell)
1) Install JDK 17+

2) Compile from the project root:
```powershell
javac -d out -sourcepath src src/edu/ccrm/cli/Main.java
```

3) Run the app:
```powershell
java -cp out edu.ccrm.cli.Main
```

4) Try the demos:
- Choose `4` for Import/Export demo (uses `test-data/` CSVs)
- Choose `5` for directory size demo (sizes for `data/`, `exports/`, `backups/`)
- Type `h` or `?` for help

## Evolution of Java (short bullets)
- 1995: Initial release
- 2004: Java 5 (Generics, Annotations)
- 2011: Java 7 (NIO.2)
- 2014: Java 8 (Lambdas, Streams, Date/Time API)
- 2017+: Time-based releases (9+), Modules (9), Records/Pattern Matching (later)

## Java ME vs SE vs EE
- Java ME: Mobile/embedded profile with limited APIs.
- Java SE: Standard Edition for desktop/server apps; core language and libraries. (CCRM uses SE)
- Java EE/Jakarta EE: Enterprise features (Servlets, EJB, JPA) for large-scale enterprise apps.

## JDK, JRE, JVM
- JVM: Virtual machine running bytecode.
- JRE: JVM + standard libraries to run apps.
- JDK: JRE + developer tools (javac, javadoc) to build apps.

## Install Java on Windows (Steps)
1. Download JDK (Temurin/Oracle) for Windows.
2. Install and set JAVA_HOME.
3. Add %JAVA_HOME%\bin to PATH.
4. Verify:
   ```powershell
   java -version
   javac -version
   ```

## Usage Overview
- Entry point: `edu.ccrm.cli.Main`
- Console utilities: `edu.ccrm.cli.Console` (ANSI colors; set `CCRM_ANSI=0` to disable)
- Import/Export demo: `edu.ccrm.io.impl.SimpleImportExportService`
- Recursive utilities: `edu.ccrm.util.RecursionUtils`
- App configuration (data dirs): `edu.ccrm.config.AppConfig`

## Mapping Table (Syllabus topic → file/class/method)
This will be completed as implementation proceeds.

## Assertions
- Enable assertions when running:
  ```powershell
  java -ea -cp out edu.ccrm.cli.Main
  ```

## Data & Directories
- `data/` is created on startup (via `AppConfig`).
- `exports/` is where demo export writes to.
- `backups/` is available for future backup logic.
- Sample CSVs are under `test-data/`.

## Troubleshooting
- Colors look weird? Disable ANSI colors via env var before running:
  ```powershell
  $env:CCRM_ANSI = "0"
  java -cp out edu.ccrm.cli.Main
  ```
- `ClassNotFoundException`? Ensure you compiled with `-d out` and are running with `-cp out` from the project root.

## (Optional) Build a Runnable JAR
```powershell
del out\*.class -ErrorAction SilentlyContinue
javac -d out -sourcepath src src/edu/ccrm/cli/Main.java
echo Main-Class: edu.ccrm.cli.Main > manifest.mf
jar cfm ccrm.jar manifest.mf -C out .
java -jar ccrm.jar
```

## Acknowledgements
- Official Java Tutorials and API docs.
