module com.example.grading_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.grading_system to javafx.fxml;
    exports com.example.grading_system;
}