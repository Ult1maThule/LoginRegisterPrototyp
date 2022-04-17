package com.example.loginregprototyp;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Alerts {
    // Register Alerts
    protected static void successAlert(Button button){
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setHeaderText("Registrierung erfolgreich!");
        successAlert.setContentText("Sie haben erfolgreich ein Projektname-Konto erstellt!");
        successAlert.showAndWait();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    protected static void emptyFieldsAlert(){
        Alert emptyFieldAlert = new Alert(Alert.AlertType.ERROR);
        emptyFieldAlert.setHeaderText("Ein oder mehrere Felder sind leer!");
        emptyFieldAlert.setContentText("Bitte alle Felder ausfüllen!");
        emptyFieldAlert.showAndWait();}

    protected static void errorMessagesOpenAlert(){
        Alert errorMessageAlert = new Alert(Alert.AlertType.ERROR);
        errorMessageAlert.setHeaderText("Fehler beim registrieren!");
        errorMessageAlert.setContentText("Bitte überprüfen Sie Ihre Eingaben!");
        errorMessageAlert.showAndWait();}

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
