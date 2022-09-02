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

public class StaffDashboardController implements Initializable {
    @FXML
    private Button btn_addStaff;
    @FXML
    private Button txt_logout;
    @FXML
    private TextField txt_id;
    @FXML
    private TextField txt_name1;
    @FXML
    private TextField txt_mark1;
    @FXML
    private TextField txt_grade1;
    @FXML
    private TextField txt_name2;
    @FXML
    private TextField txt_mark2;
    @FXML
    private TextField txt_grade2;
    @FXML
    private Button btn_save;
    @FXML
    private Button btn_update;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txt_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DatabaseConnector.changeScene(actionEvent, "loginStaff.fxml", "Login Staff");
            }
        });

        btn_save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!txt_id.getText().trim().isEmpty() && !txt_name1.getText().trim().isEmpty() && !txt_mark1.getText().trim().isEmpty() && !txt_grade1.getText().trim().isEmpty()){
                    DatabaseConnector.save(actionEvent, txt_id.getText(), txt_name1.getText(), txt_mark1.getText(), txt_grade1.getText(), txt_name2.getText(), txt_mark2.getText(), txt_grade2.getText());
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Enter Subject Info to Save");
                    alert.show();
                }
            }
        });


        btn_addStaff.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               DatabaseConnector.changeScene(actionEvent,"registerStaff.fxml","Register Staff");
            }
        });
    }
}
