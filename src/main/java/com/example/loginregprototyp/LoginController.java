package com.example.loginregprototyp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;

public class LoginController {

    @FXML
    private CheckBox checkbox;

    @FXML
    private Button done_button;

    @FXML
    private PasswordField password_textfield;

    @FXML
    private Button register_button;

    @FXML
    private TextField username_textfield;

    @FXML
    public void onDoneButtonEvent(ActionEvent event) {
        try {
            if (verifyTextFields()) {

            }

            usernameCheck(username_textfield.getText());
            passwordCheck(password_textfield.getText());

            //TODO Weiterleitung zur Mainpage
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onRegisterButtonEvent(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(RegisterController.class.getResource("registerView.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void giveSQLAlert() {
        Alert sqlconnectionError = new Alert(Alert.AlertType.ERROR);
        sqlconnectionError.setHeaderText("Fehler beim Verbinden mit Datenbank!");
        sqlconnectionError.setContentText("Überprüfen Sie die Verbindung zur Datenbank auf Fehler!");
        sqlconnectionError.showAndWait();
    }

    private void giveEmptyTextfieldAlert() {

    }

    private boolean verifyTextFields() {
        String username = username_textfield.getText();
        String password = password_textfield.getText();
        boolean textfield_empty = false;

        if (username.equals("") || password.equals("")) {
            textfield_empty = true;
            Alert emptyFieldAlert = new Alert(Alert.AlertType.ERROR);
            emptyFieldAlert.setHeaderText("Ein oder mehrere Felder sind leer!");
            emptyFieldAlert.setContentText("Bitte alle Felder ausfüllen!");
            emptyFieldAlert.showAndWait();

            username_textfield.clear();
            password_textfield.clear();
        }

        return textfield_empty;
    }

    private boolean usernameCheck(String username) throws SQLException {
        Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String query = "select * from users where username=?";
        boolean wrong_username = true;

        try {
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                wrong_username = false;
            } else {
                Alert wrongUsernameAlert = new Alert(Alert.AlertType.ERROR);
                wrongUsernameAlert.setHeaderText("Falscher Benutzername!");
                wrongUsernameAlert.setContentText("Benutzername nicht gefunden, bitte erneut eintragen!");
                wrongUsernameAlert.showAndWait();

                username_textfield.clear();
            }
        } catch (SQLException e) {
            giveSQLAlert();
        }

        return wrong_username;
    }

    private boolean passwordCheck(String password) throws SQLException {
        Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String query = "select * from users where password=?";
        boolean wrong_password = true;

        try {
            ps = con.prepareStatement(query);
            ps.setString(1, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                wrong_password = false;
            } else {
                Alert wrongPasswordAlert = new Alert(Alert.AlertType.ERROR);
                wrongPasswordAlert.setHeaderText("Falsches Passwort!");
                wrongPasswordAlert.setContentText("Benutzername nicht gefunden, bitte erneut eintragen!");
                wrongPasswordAlert.showAndWait();

                password_textfield.clear();
            }
        } catch (SQLException e) {
            giveSQLAlert();
        }

        return wrong_password;
    }
}
