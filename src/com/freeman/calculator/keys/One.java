package com.freeman.calculator.keys;

import com.freeman.calculator.Key;
import com.freeman.calculator.KeyAction;

/**
 * Created by freeman on 30.07.2016.
 */
public class One extends Key {
    private String action;

    public One() {
        this.action = KeyAction.ONE.getAction();
    }

    @Override
    protected boolean isOperational() {
        return false;
    }

    @Override
    protected String performAction() {
        return action;
    }


}
