package com.freeman.calculator;

import com.freeman.calculator.keys.Equals;
import com.freeman.calculator.keys.One;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by freeman on 30.07.2016.
 *
 * Holds all calculator keys.
 */
public class Keyboard {
    private List<Key> keys;

    public Keyboard() {
        this.keys = new ArrayList<>();

        keys.add(new One());

        keys.add(new Equals());
    }

    public List<Key> getKeys() {
        return this.keys;
    }
}
