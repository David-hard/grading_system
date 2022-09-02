package com.example.grading_system;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginStaffController implements Initializable {
    @FXML
    private TextField txt_username;
    @FXML
    private Button btn_login;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Button btn_back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DatabaseConnector.changeScene(actionEvent, "loginStudent.fxml", "Log In");
            }
        });

        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!txt_username.getText().trim().isEmpty() && !txt_password.getText().trim().isEmpty()){
                    DatabaseConnector.logInAdmin(actionEvent, txt_username.getText(), txt_password.getText());
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Enter username and password");
                    alert.show();
                }
            }
        });
    }
}
