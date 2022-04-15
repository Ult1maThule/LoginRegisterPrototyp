package com.example.loginregprototyp;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField username_textfield;

    @FXML
    private TextField password_textfield;

    @FXML
    private Button done_button;

    @FXML
    private CheckBox checkbox;

    @FXML
    private Button register_button;

    public LoginController() {

    }

    public boolean verifyTextFields() {
        String username = username_textfield.getText();
        String password = password_textfield.getText();

        if (username.equals("") || password.equals("")) {
            Alert emptyFieldAlert = new Alert(Alert.AlertType.ERROR);
            emptyFieldAlert.setHeaderText("Ein oder mehrere Felder sind leer!");
            emptyFieldAlert.setContentText("Bitte alle Felder ausfüllen!");
            emptyFieldAlert.showAndWait();

            username_textfield.clear();
            password_textfield.clear();
        }

        return true;
    }

    public boolean usernameCheck(String username) throws SQLException {
        Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String query = "select * from users where username=?";
        boolean username_exists = false;

        try {
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                username_exists = true;
            } else {
                Alert wrongUsernameAlert = new Alert(Alert.AlertType.ERROR);
                wrongUsernameAlert.setHeaderText("Falscher Benutzername!");
                wrongUsernameAlert.setContentText("Benutzername nicht gefunden, bitte erneut eintragen!");
                wrongUsernameAlert.showAndWait();

                username_textfield.clear();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username_exists;
    }

    public boolean passwordCheck(String password) throws SQLException {
        Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String query = "select * from users where password=?";
        boolean password_exists = false;

        try {
            ps = con.prepareStatement(query);
            ps.setString(1, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                password_exists = true;
            } else {
                Alert wrongPasswordAlert = new Alert(Alert.AlertType.ERROR);
                wrongPasswordAlert.setHeaderText("Falsches Passwort!");
                wrongPasswordAlert.setContentText("Benutzername nicht gefunden, bitte erneut eintragen!");
                wrongPasswordAlert.showAndWait();

                password_textfield.clear();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password_exists;
    }
}

