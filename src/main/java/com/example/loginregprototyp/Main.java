package com.example.loginregprototyp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlloader = new FXMLLoader(LoginController.class.getResource("loginView.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(fxmlloader.load()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
