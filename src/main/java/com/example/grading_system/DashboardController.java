package com.example.grading_system;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {


    @FXML
    private Button txt_logout1;
    @FXML
    private TextField txt_search;
    @FXML
    private Label lb_name1;
    @FXML
    private Label lb_mark1;
    @FXML
    private Label lb_grade1;
    @FXML
    private Label lb_name2;
    @FXML
    private Label lb_mark2;
    @FXML
    private Label lb_grade2;
    @FXML
    private Button txt_logout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txt_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DatabaseConnector.changeScene(actionEvent, "loginStudent.fxml", "Log In");
            }
        });


        txt_logout1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

//                String studentId = null;
//                String subject;
//                String mark;
//                String grade;
//                String subject2;
//                String mark2;
//                String grade2;

                Connection connection = null;
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;

                try {
                    String studentId = txt_search.getText();
                    connection = DriverManager.getConnection("jdbc:mysql://localhost/grading_system_db", "root", "");

                    preparedStatement = connection.prepareStatement("SELECT subject, mark, grade, subject2, mark2, grade2 FROM addmarks WHERE studentId = ?");
                    preparedStatement.setString(1, studentId);
                    resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()==true){
                        String name1 = resultSet.getString(1);
                        String mark1 = resultSet.getString(2);
                        String grade1 = resultSet.getString(3);
                        String name2 = resultSet.getString(4);
                        String mark22 = resultSet.getString(5);
                        String grade22 = resultSet.getString(6);


                        lb_name1.setText(name1);
                        lb_mark1.setText(mark1);
                        lb_grade1.setText(grade22);
                        lb_name2.setText(name2);
                        lb_mark2.setText(mark22);
                        lb_grade2.setText(grade22);

                    }
                } catch (SQLException e){
                    e.printStackTrace();
                }
                //DatabaseConnector.search(actionEvent, txt_search.getText(), lb_name1.getText(), lb_mark1.getText(), lb_grade1.getText(), lb_name2.getText(), lb_mark2.getText(),lb_grade2.getText());
            }
        });
    }
}
