//@author Vinusan Sivalingam
package com.example.loginregprototyp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;

import java.sql.*;
import java.util.EventListener;
import java.util.regex.Pattern;

public class RegisterController {

    @FXML
    private Button continueButton, backToLoginButton;

    @FXML
    private Label emailTakenError, nameErrorLabel, passwordErrorLabel, usernameTakenLabel, adminBestĂ¤tigungLabel;

    @FXML
    private PasswordField setPasswordfield, confirmPasswordfield;

    @FXML
    private CheckBox showPasswordCheckBox;

    @FXML
    private TextField emailTextfield, lastnameTextfield, usernameTextfield, firstnameTextfield, showPasswordTextfield,showConfirmPasswordTextfield;

    private boolean errorAlertExecuted = false;


    public void initialize(){
        checkNameFormat(firstnameTextfield,lastnameTextfield);
        checkNameFormat(lastnameTextfield,firstnameTextfield);
        toggleVisiblePassword(null);
        checkEmailAvailabilityAndRights(emailTextfield);
        checkUsernameAvailability(usernameTextfield);
        checkPassword();

    }

    @FXML
    public void onContinueButtonEvent(ActionEvent event) {

        errorAlert();
            if(!errorAlertExecuted){
               String firstname = firstnameTextfield.getText();
               String lastname  = lastnameTextfield.getText();
               String username  = usernameTextfield.getText();
               String email     = emailTextfield.getText();
               String password  = setPasswordfield.getText();
               Admin a = new Admin(firstname,lastname,username,email,password);
               registerAdmin(a);
               Alerts.successAlert(continueButton);

            } else {
                errorAlertExecuted=false;
            }

    }
    @FXML
    public void toggleVisiblePassword(ActionEvent event) {
        if (showPasswordCheckBox.isSelected()) {
            showPasswordTextfield.setText(setPasswordfield.getText());
            showPasswordTextfield.setVisible(true);
            setPasswordfield.setVisible(false);
            //nochmal fĂĽr das zweite Passwortfeld
            showConfirmPasswordTextfield.setText(confirmPasswordfield.getText());
            showConfirmPasswordTextfield.setVisible(true);
            confirmPasswordfield.setVisible(false);
            return;
        }
        setPasswordfield.setText(showPasswordTextfield.getText());
        setPasswordfield.setVisible(true);
        showPasswordTextfield.setVisible(false);
        //nochmal fĂĽr das zweite Passwortfeld
        confirmPasswordfield.setText(showConfirmPasswordTextfield.getText());
        confirmPasswordfield.setVisible(true);
        showConfirmPasswordTextfield.setVisible(false);
    }

    @FXML
    public void onBackToLoginButtonEvent() {
        ((Stage) continueButton.getScene().getWindow()).close();
    }

    public void registerAdmin(Admin a)  {


        try{
            Connection con = DatabaseConnection.getConnection();
            String insertFields = "insert into users (firstname,lastname, username, email, password) values "+""+"('"+a.getFirstname()+"','"+a.getLastname()+"','"+a.getUsername()+"','"+a.getEmail()+"','"+a.getPassword()+"')";
            String insertToRegister= insertFields;
            Statement Insert = con.createStatement();
            Insert.execute(insertToRegister);
            Insert.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    private void errorAlert(){
        if(isEmptyField(firstnameTextfield)||isEmptyField(lastnameTextfield)||isEmptyField(usernameTextfield)||isEmptyField(emailTextfield)||isEmptyPasswordfield(setPasswordfield)||isEmptyPasswordfield(confirmPasswordfield)||isEmptyField(showPasswordTextfield)||isEmptyField(showConfirmPasswordTextfield)){
            errorAlertExecuted=true;
            Alerts.emptyFieldsAlert();

        } else if(checkErrormessage(usernameTakenLabel)||checkErrormessage(emailTakenError)||checkErrormessage(nameErrorLabel)||checkErrormessage(passwordErrorLabel)||checkErrormessage(usernameTakenLabel)){
            errorAlertExecuted=true;
            Alerts.errorMessagesOpenAlert();

        }

    }

    private boolean isEmptyField(TextField field){
        if(field.getText().equals("")){
            return true;
        }
        return false;
    }

    private boolean isEmptyPasswordfield(PasswordField field){
        if(field.getText().equals("")){
            return true;
        }
        return false;
    }

    private boolean checkErrormessage(Label errormessage){
        if(errormessage.getText()!=""){
            return true;
        }
        return false;
    }


    private void checkUsernameAvailability(TextField name){
        name.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //Datenbank prĂĽfe ob username schon existiert--> neue Methode
                try {
                    usernameCheck(name.getText());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void checkEmailAvailabilityAndRights(TextField name){
        name.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //prĂĽfe on Email stimmt
                String pattern = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
                if(Pattern.compile(pattern).matcher(name.getText()).matches()){
                    emailTakenError.setText("");
                    try {
                        emailCheck(name.getText());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    emailTakenError.setText("Dies ist keine gĂĽltige E-Mail Addresse !");
                }
                //Datenbank: prĂĽfe ob existiert --> neue Methode
                // emailTakenError.setText("Die E-Mail-Adresse wird bereits verwendet !");

                //Datenbank: prĂĽfe ob Email auf Whitelist
                //adminBestĂ¤tigungLabel.setText("Hinweis: Sie melden sich als ein Admin an");
            }
        });
    }

    private void checkPassword(){
        confirmPasswordfield.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (setPasswordfield.getText().equals(confirmPasswordfield.getText()) && showPasswordTextfield.getText().equals(showConfirmPasswordTextfield.getText())) {
                    passwordErrorLabel.setText("");
                }else {
                    passwordErrorLabel.setText("Keine Ăśbereinstimmung !");
                }

            }
        });
    }

    private void checkNameFormat(TextField name,TextField name2){
        name.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!name.getText().matches("^[a-zA-Z\\s]*$")||!name2.getText().matches("^[a-zA-Z\\s]*$+")){
                    nameErrorLabel.setText("Haben Sie Ihren Namen richtig eingegeben?");
                } else {
                    nameErrorLabel.setText("");

                }
            }
        });
    }

    private void emailCheck(String email) throws SQLException {
        Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String query= "select * from users where email=?";

        try {
            ps = con.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (!rs.next()) {

            } else {
              emailTakenError.setText("Die Email-Addresse ist bereits vergeben");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void usernameCheck(String username) throws SQLException {
        Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String query= "select * from users where username=?";

        try {
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (!rs.next()) {
                usernameTakenLabel.setText("");
            } else{
                usernameTakenLabel.setText("Der Username ist bereits vergeben");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}

