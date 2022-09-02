package com.example.grading_system;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterStaffController implements Initializable {
    @FXML
    private TextField txt_fname;
    @FXML
    private TextField txt_lname;
    @FXML
    private TextField txt_studentId;
    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;
    @FXML
    private Button btn_register;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!txt_fname.getText().trim().isEmpty() && !txt_lname.getText().trim().isEmpty() && !txt_studentId.getText().trim().isEmpty() && !txt_username.getText().trim().isEmpty()
                        && !txt_password.getText().trim().isEmpty()){
                    DatabaseConnector.signUpStaff(actionEvent, txt_fname.getText(), txt_lname.getText(), txt_studentId.getText(), txt_username.getText(), txt_password.getText());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Enter all information");
                    alert.show();
                }
            }
        });
    }
}
