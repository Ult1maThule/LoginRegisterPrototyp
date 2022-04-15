module com.example.loginregprototyp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.loginregprototyp to javafx.fxml;
    exports com.example.loginregprototyp;
}