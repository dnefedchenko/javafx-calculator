package com.freeman.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**
 * Created by freeman on 31.07.2016.
 */
public class CalculatorApplication extends Application {
    private static String STYLESHEET_PATH = "calculator.css";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CalculatorApplication.class.getResource("view/root-layout.fxml"));
        loader.setResources(ResourceBundle.getBundle("calculator"));
        VBox rootLayout = loader.load();

        Scene scene = new Scene(rootLayout);
        scene.getStylesheets().add(STYLESHEET_PATH);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
