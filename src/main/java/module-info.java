module com.example.loginregprototyp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.loginregprototyp to javafx.fxml;
    exports com.example.loginregprototyp;
}