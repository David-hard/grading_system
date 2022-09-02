package com.example.grading_system;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginStudentController implements Initializable {
    @FXML
    private TextField txt_username;
    @FXML
    private Button btn_login;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Button btn_staff;
    @FXML
    private Button txt_register;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txt_register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DatabaseConnector.changeScene(actionEvent, "registerStudent.fxml", "Register");
            }
        });

        btn_staff.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DatabaseConnector.changeScene(actionEvent, "loginStaff.fxml", "Login Staff");
            }
        });

        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!txt_username.getText().trim().isEmpty() && !txt_password.getText().trim().isEmpty()){
                    DatabaseConnector.logIn(actionEvent, txt_username.getText(), txt_password.getText());
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Enter username and password");
                    alert.show();
                }
            }
        });
    }
}