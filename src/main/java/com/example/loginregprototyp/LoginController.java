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
                Alerts.giveEmptyTextfieldAlert(username_textfield, password_textfield);
            } else if (usernameCheck(username_textfield.getText())) {
                Alerts.giveWrongUsernameAlert(username_textfield);
            } else if (passwordCheck(password_textfield.getText())) {
                Alerts.giveWrongPasswordAlert(password_textfield);
            } else {
                FXMLLoader fxmlLoader = new FXMLLoader(MainpageFXController.class.getResource("mainpageView.fxml"));
                Stage loginStage = (Stage) done_button.getScene().getWindow();
                Stage mainpageStage = new Stage();
                mainpageStage.setScene(new Scene(fxmlLoader.load()));
                loginStage.close();
                mainpageStage.show();
            }
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

    private boolean verifyTextFields() {
        String username = username_textfield.getText();
        String password = password_textfield.getText();
        boolean textfield_empty = false;

        if (username.equals("") || password.equals("")) {
            textfield_empty = true;
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
            }
        } catch (SQLException e) {
            Alerts.giveSQLAlert();
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
            }
        } catch (SQLException e) {
            Alerts.giveSQLAlert();
        }

        return wrong_password;
    }
}
