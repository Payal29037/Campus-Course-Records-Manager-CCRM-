package edu.ccrm.cli;

/**
 * Small console helper for colored outputs and safe prompts.
 * Uses ANSI colors where supported; falls back to plain text on unsupported terminals.
 */
public final class Console {
    private static final boolean ANSI_ENABLED = detectAnsiSupport();

    private Console() {}

    public static String color(String text, String ansiCode) {
        if (!ANSI_ENABLED) return text;
        return ansiCode + text + "\u001B[0m";
    }

    public static String green(String text) { return color(text, "\u001B[32m"); }
    public static String yellow(String text) { return color(text, "\u001B[33m"); }
    public static String red(String text) { return color(text, "\u001B[31m"); }
    public static String cyan(String text) { return color(text, "\u001B[36m"); }
    public static String bold(String text) { return color(text, "\u001B[1m"); }

    public static void println(String text) { System.out.println(text); }
    public static void print(String text) { System.out.print(text); }

    public static String prompt(String message) {
        java.io.Console sysConsole = System.console();
        if (sysConsole != null) {
            return sysConsole.readLine("%s", message);
        } else {
            System.out.print(message);
            try {
                byte[] buf = new byte[1024];
                int len = System.in.read(buf);
                return new String(buf, 0, Math.max(0, len)).trim();
            } catch (Exception e) {
                return "";
            }
        }
    }

    private static boolean detectAnsiSupport() {
        // Basic heuristic: many modern Windows terminals support ANSI; older ones may not.
        // Allow overriding via env var CCRM_ANSI=1/0
        String env = System.getenv("CCRM_ANSI");
        if (env != null) {
            return env.equals("1") || env.equalsIgnoreCase("true");
        }
        String os = System.getProperty("os.name", "").toLowerCase();
        if (os.contains("win")) {
            // Assume supported on Windows 10+ terminals, PowerShell, and IDE terminals
            return true;
        }
        return true; // most UNIX-like terminals support ANSI
    }
}
