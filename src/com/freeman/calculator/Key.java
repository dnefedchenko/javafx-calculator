package com.freeman.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created by freeman on 29.07.2016.
 */
public abstract class Key extends Button {
    protected static Display display = new Display();

    /**
     * Indicated whether key is plain input or operational one.
     * Typical examples of the input keys are <em>1</em>, <em>+</em>, <em>.</em>.
     * Example of the operational key is <em>=</em>
     *
     * @return true if key is operational and false otherwise
     */
    protected boolean isOperational() {
        return false;
    }

    @FXML
    public void push() {
        String result = performAction();
        display.show(result);
        resetIfOperationPerformed();
    }

    private void resetIfOperationPerformed() {
        if (isOperational()) {
            display.reset();
        }
    }

    protected abstract String performAction();
}
