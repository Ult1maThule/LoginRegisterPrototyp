package com.example.loginregprototyp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainpageFXController {

    @FXML
    private Button closeMainpageButton;

    //Wenn der Schließen Button geklickt wird, wird die Seite geschlossen
    @FXML
    void onCloseButtonAction(ActionEvent event) {
        ((Stage) closeMainpageButton.getScene().getWindow()).close();
    }

}

