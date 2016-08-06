package com.freeman.calculator.controller;

import com.freeman.calculator.KeyAction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Created by freeman on 31.07.2016.
 */
public class CalculatorController {
    @FXML private TextField displayField;
    @FXML private GridPane keyboard;

    private StringProperty currentInput = new SimpleStringProperty("");

    @FXML
    private void initialize() {
        displayField.textProperty().bindBidirectional(currentInput);
        initKeyListeners();
    }

    private void initKeyListeners() {
        keyboard.getChildren().forEach(key -> {
            ((Button)key).setOnAction(event -> {
                handleInput(KeyAction.valueOf(key.getId()));
            });
        });
    }

    private void handleInput(KeyAction keyAction) {
        if (KeyAction.EQUALS == keyAction) {
            calculateResult();
        } else {
            processInput(keyAction.getAction());
        }
    }

    private void calculateResult() {
        System.out.println("Calculating " + currentInput.get() + " expression value");
    }

    private void processInput(String input) {
        currentInput.set(currentInput.get().concat(input));
    }
}
