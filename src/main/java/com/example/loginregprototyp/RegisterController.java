package com.example.loginregprototyp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;

import java.util.EventListener;

public class RegisterController {


    @FXML
    private Label adminBestätigungLabel;

    @FXML
    private PasswordField confirmPasswordfield;

    @FXML
    private Button continueButton;

    @FXML
    private Label emailTakenError;

    @FXML
    private TextField emailTextfield;

    @FXML
    private TextField lastnameTextfield;

    @FXML
    private Label nameErrorLabel;

    @FXML
    private Label passwordErrorLabel;

    @FXML
    private PasswordField setPasswordfield;

    @FXML
    private CheckBox showPasswordCheckBox;

    @FXML
    private Label usernameTakenLabel;

    @FXML
    private TextField usernameTextfield;

    @FXML
    private TextField firstnameTextfield;

    @FXML
    private TextField showPasswordTextfield;

    @FXML
    private TextField showConfirmPasswordTextfield;



    public void initialize(){
        checkNameFormat(firstnameTextfield);
        checkNameFormat(lastnameTextfield);
        toggleVisiblePassword(null);
    }

    @FXML
    public void onContinueButtonEvent(ActionEvent event) {
        Stage stage = (Stage) continueButton.getScene().getWindow();
        stage.close();
    }

    private void checkNameFormat(TextField name){
        name.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!name.getText().matches("[a-zA-Z]+")){
                    nameErrorLabel.setText("Haben Sie Ihren Namen richtig eingegeben?");
                } else {
                    nameErrorLabel.setText(" ");
                }
            }
        });
    }
    @FXML
    public void toggleVisiblePassword(ActionEvent event) {
        if (showPasswordCheckBox.isSelected()) {
            showPasswordTextfield.setText(setPasswordfield.getText());
            showPasswordTextfield.setVisible(true);
            setPasswordfield.setVisible(false);
            //nochmal für das zweite Passwortfeld
            showConfirmPasswordTextfield.setText(confirmPasswordfield.getText());
            showConfirmPasswordTextfield.setVisible(true);
            confirmPasswordfield.setVisible(false);
            return;
        }
        setPasswordfield.setText(showPasswordTextfield.getText());
        setPasswordfield.setVisible(true);
        showPasswordTextfield.setVisible(false);
        //nochmal für das zweite Passwortfeld
        confirmPasswordfield.setText(showConfirmPasswordTextfield.getText());
        confirmPasswordfield.setVisible(true);
        showConfirmPasswordTextfield.setVisible(false);
    }
    private void checkUsernameAvailability(TextField name){
        name.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //Datenbank prüfe ob username schon existiert--> neue Methode
                usernameTakenLabel.setText("Der Username wird bereits verwendet !");

            }
        });
    }

    private void checkEmailAvailabilityAndRights(TextField name){
        name.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //Datenbank: prüfe ob existiert --> neue Methode
                emailTakenError.setText("Die E-Mail-Adresse wird bereits verwendet !");
                //Datenbank: prüfe ob Email auf Whitelist
                adminBestätigungLabel.setText("Hinweis: Sie melden sich als ein Admin an");
            }
        });
    }

}

