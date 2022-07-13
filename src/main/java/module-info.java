module com.example.folk_cuisine_fair {
    requires javafx.controls;
    requires javafx.fxml;


    opens controllers to javafx.fxml;
    exports controllers;
}