package com.freeman.calculator;

import com.freeman.calculator.controller.CalculatorController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ResourceBundle;

/**
 * Created by freeman on 31.07.2016.
 */
public class Calculator extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Calculator.class.getResource("view/root-layout.fxml"));
        loader.setResources(ResourceBundle.getBundle("calculator"));
        GridPane rootLayout = loader.load();
        rootLayout.setGridLinesVisible(true);


        Scene scene = new Scene(rootLayout);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
