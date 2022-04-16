package com.example.loginregprototyp;

import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Alerts {
    // Login Alerts
    public static void giveSQLAlert() {
        Alert sqlconnectionError = new Alert(Alert.AlertType.ERROR);
        sqlconnectionError.setHeaderText("Fehler beim Verbinden mit Datenbank!");
        sqlconnectionError.setContentText("Überprüfen Sie die Verbindung zur Datenbank auf Fehler!");
        sqlconnectionError.showAndWait();
    }

    public static void giveEmptyTextfieldAlert(TextField textField, PasswordField passwordField) {
        Alert emptyFieldAlert = new Alert(Alert.AlertType.ERROR);
        emptyFieldAlert.setHeaderText("Ein oder mehrere Felder sind leer!");
        emptyFieldAlert.setContentText("Bitte alle Felder ausfüllen!");
        emptyFieldAlert.showAndWait();

        textField.clear();
        passwordField.clear();
    }

    public static void giveWrongUsernameAlert(TextField textField) {
        Alert wrongUsernameAlert = new Alert(Alert.AlertType.ERROR);
        wrongUsernameAlert.setHeaderText("Falscher Benutzername!");
        wrongUsernameAlert.setContentText("Benutzername nicht gefunden, bitte erneut eintragen!");
        wrongUsernameAlert.showAndWait();

        textField.clear();
    }

    public static void giveWrongPasswordAlert(TextField textField) {
        Alert wrongPasswordAlert = new Alert(Alert.AlertType.ERROR);
        wrongPasswordAlert.setHeaderText("Falsches Passwort!");
        wrongPasswordAlert.setContentText("Benutzername nicht gefunden, bitte erneut eintragen!");
        wrongPasswordAlert.showAndWait();

        textField.clear();
    }
}
