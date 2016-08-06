package com.freeman.calculator.keys;

import com.freeman.calculator.Key;
import com.freeman.calculator.KeyAction;

/**
 * Created by freeman on 30.07.2016.
 */
public class Equals extends Key {
    private String action;

    public Equals() {
        this.action = KeyAction.EQUALS.getAction();
    }

    @Override
    protected boolean isOperational() {
        return true;
    }

    @Override
    protected String performAction() {
        return calculateResult(display.getCurrentInput());
    }

    private String calculateResult(String input) {
        return null;
    }
}
