package com.freeman.calculator;

import java.util.regex.Pattern;

/**
 * Created by Dmitriy Nefedchenko on 27.10.2016.
 */
public class InputFormatter {
    private static Pattern LONG_FLOATING_PART_PATTERN = Pattern.compile("(.\\d{8,})");
    private static Pattern ALL_TRAILING_ZEROS_PATTERN = Pattern.compile("(\\d+.0*)");

    public static String format(String input) {
        if (!isDigit(input)) {
            return input;
        }
        if (ALL_TRAILING_ZEROS_PATTERN.matcher(input).matches()) {
            return input.substring(0, input.indexOf('.'));
        }
        return LONG_FLOATING_PART_PATTERN.matcher(input).find()
                ? input.substring(0, input.indexOf('.') + 8) : input;
    }

    private static boolean isDigit(String input) {
        return !input.codePoints().filter(codePoint -> Character.isAlphabetic(codePoint)).findAny().isPresent();
    }
}
