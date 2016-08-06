package com.freeman.calculator.controller;

import com.freeman.calculator.Key;
import com.freeman.calculator.keys.One;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Created by freeman on 31.07.2016.
 */
public class CalculatorController {
    @FXML private TextField displayField;

/*    @FXML private Button sevenKey;
    @FXML private Button eightKey;
    @FXML private Button nineKey;
    @FXML private Button divideKey;

    @FXML private Button fourKey;
    @FXML private Button fiveKey;
    @FXML private Button sixKey;
    @FXML private Button multiplyKey;

    @FXML private Button oneKey;
    @FXML private Button twoKey;
    @FXML private Button threeKey;
    @FXML private Button minusKey;

    @FXML private Button zeroKey;
    @FXML private Button pointKey;
    @FXML private Button equalsKey;
    @FXML private Button plusKey;*/

    @FXML
    private void initialize() {

    }

    @FXML
    private void push(ActionEvent event) {
        System.out.println("Event type is: " + event.getEventType());
    }
}
