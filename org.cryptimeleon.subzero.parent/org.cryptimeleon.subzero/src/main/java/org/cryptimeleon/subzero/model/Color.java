package org.cryptimeleon.subzero.model;

/**
 * Provides helper methods for coloring console output for debugging purposes
 */
public class Color {
    public static final String BLACK = "\033[0;30m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String MAGENTA = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String WHITE = "\033[0;37m";

    public static final String BLACK_BRIGHT = "\033[0;90m";
    public static final String RED_BRIGHT = "\033[0;91m";
    public static final String GREEN_BRIGHT = "\033[0;92m";
    public static final String YELLOW_BRIGHT = "\033[0;93m";
    public static final String BLUE_BRIGHT = "\033[0;94m";
    public static final String MAGENTA_BRIGHT = "\033[0;95m";
    public static final String CYAN_BRIGHT = "\033[0;96m";
    public static final String WHITE_BRIGHT = "\033[0;97m";

    public static final String RESET = "\033[0m";

    private static boolean colorDisabled = false;

    private Color() {
        throw new AssertionError();
    }

    public static String black(String input) {
        return color(input, BLACK);
    }

    public static String red(String input) {
        return color(input, RED);
    }

    public static String green(String input) {
        return color(input, GREEN);
    }

    public static String yellow(String input) {
        return color(input, YELLOW);
    }

    public static String blue(String input) {
        return color(input, BLUE);
    }

    public static String magenta(String input) {
        return color(input, MAGENTA);
    }

    public static String cyan(String input) {
        return color(input, CYAN);
    }

    public static String white(String input) {
        return color(input, WHITE);
    }

    public static String brightBlack(String input) {
        return color(input, BLACK_BRIGHT);
    }

    public static String brightRed(String input) {
        return color(input, RED_BRIGHT);
    }

    public static String brightGreen(String input) {
        return color(input, GREEN_BRIGHT);
    }

    public static String brightYellow(String input) {
        return color(input, YELLOW_BRIGHT);
    }

    public static String brightBlue(String input) {
        return color(input, BLUE_BRIGHT);
    }

    public static String brightMagenta(String input) {
        return color(input, MAGENTA_BRIGHT);
    }

    public static String brightCyan(String input) {
        return color(input, CYAN_BRIGHT);
    }

    public static String brightWhite(String input) {
        return color(input, WHITE_BRIGHT);
    }

    public static void disableColor() {
        colorDisabled = true;
    }

    private static String color(String input, String textColor) {
        if (colorDisabled) return input;
        return textColor + input + RESET;
    }
}
