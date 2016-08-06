package com.freeman.calculator;

/**
 * Created by freeman on 30.07.2016.
 *
 *  Displays input operations and calculated result.
 */
public class Display {
    private String currentInput;

    public void show(String result) {

    }

    public void reset() {
        this.currentInput = "";
    }

    public String getCurrentInput() {
        return this.currentInput;
    }
}
