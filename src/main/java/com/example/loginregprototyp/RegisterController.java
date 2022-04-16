package com.example.loginregprototyp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;

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



    public void initialize(){
       checkNameFormat(firstnameTextfield);
       checkNameFormat(lastnameTextfield);
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

}

