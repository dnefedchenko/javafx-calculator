package com.freeman.calculator.util;

/**
 * Created by freeman on 30.07.2016.
 *
 *  Holds key actions
 */
public enum KeyAction {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    ZERO("0"),
    POINT("."),
    DIVIDE("\u00F7"),
    MULTIPLY("\u00D7"),
    PLUS("\u002B"),
    MINUS("\u2212"),
    EQUALS("\u003D"),
    POWER("^");

    public String action;

    KeyAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
