package com.example.grading_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DatabaseConnector {

    public static void changeScene(ActionEvent event, String fxmlFile, String title){
        Parent root = null;


        try {
            root = FXMLLoader.load(DatabaseConnector.class.getResource(fxmlFile));
        } catch (IOException e){
            e.printStackTrace();
        }


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 960, 700));
        stage.show();
    }




    public static void signUp(ActionEvent event, String fName, String lName, String studentId, String username, String password){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:/grading_system_db",
                    "root", "");
            psCheckUserExist = connection.prepareStatement("SELECT * FROM register_student WHERE username = ?");
            psCheckUserExist.setString(1, username);
            resultSet = psCheckUserExist.executeQuery();

            if (resultSet.isBeforeFirst()){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username");
                alert.show();
            } else {

                String sql = "INSERT INTO register_student (fName, lName, studentId, username, password) VALUES (?,?,?,?,?)";
                psInsert = connection.prepareStatement(sql);
                psInsert.setString(1, fName);
                psInsert.setString(2, lName);
                psInsert.setString(3, studentId);
                psInsert.setString(4, username);
                psInsert.setString(5, password);

                int row = psInsert.executeUpdate();
                if (row > 0){
                    System.out.println("A new user was inserted successfully!");
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("A new user added successfully!");
                    alert.show();

                }

                //changeScene(event, "dashboard.fxml", "Dashboard", username);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psCheckUserExist != null){
                try {
                    psCheckUserExist.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psInsert != null){
                try {
                    psInsert.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }
    }





    public static void logIn(ActionEvent actionEvent, String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/grading_system_db",
                    "root", "");
            preparedStatement = connection.prepareStatement("SELECT password FROM register_student WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()){
                System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            } else {
                while (resultSet.next()){
                    String retrievedPassword = resultSet.getString("password");

                    if (retrievedPassword.equals(password)){
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setContentText("Login successful!");
                        alert.show();
                        changeScene(actionEvent, "dashboard.fxml", "Welcome");
                    } else {
                        System.out.println("Password did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect!");
                        alert.show();
                    }
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if (connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }





    public static void logInAdmin(ActionEvent actionEvent, String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/grading_system_db",
                    "root", "");
            preparedStatement = connection.prepareStatement("SELECT password FROM register_staff WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()){
                System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            } else {
                while (resultSet.next()){
                    String retrievedPassword = resultSet.getString("password");

                    if (retrievedPassword.equals(password)){
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setContentText("Login successful!");
                        alert.show();
                        changeScene(actionEvent, "staffDashboard.fxml", "Welcome");
                    } else {
                        System.out.println("Password did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect!");
                        alert.show();
                    }
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if (connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }





    public static void save(ActionEvent event, String id, String name1, String mark1, String grade1, String name2, String mark2, String grade2){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/grading_system_db",
                    "root", "");
            psCheckUserExist = connection.prepareStatement("SELECT * FROM  addmarks WHERE studentId = ?");
            psCheckUserExist.setString(1, id);
            resultSet = psCheckUserExist.executeQuery();

            if (resultSet.isBeforeFirst()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Mark already added");
                alert.show();
            } else {

                String sql = "INSERT INTO addmarks (studentId, subject, mark, grade, subject2, mark2, grade2) VALUES (?,?,?,?,?,?,?)";
                psInsert = connection.prepareStatement(sql);
                psInsert.setString(1, id);
                psInsert.setString(2, name1);
                psInsert.setString(3, mark1);
                psInsert.setString(4, grade1);
                psInsert.setString(5, name2);
                psInsert.setString(6, mark2);
                psInsert.setString(7, grade2);

                int row = psInsert.executeUpdate();
                if (row > 0){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Mark sava successfully!");
                    alert.show();

                }

                //changeScene(event, "dashboard.fxml", "Dashboard", username);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psCheckUserExist != null){
                try {
                    psCheckUserExist.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psInsert != null){
                try {
                    psInsert.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }
    }






    public static void search(ActionEvent actionEvent, String studentId, String subject, String mark, String grade, String subject2, String mark2, String grade2 ){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
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



            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }



    public static void signUpStaff(ActionEvent event, String fName, String lName, String staffId, String username, String password){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:/grading_system_db",
                    "root", "");
            psCheckUserExist = connection.prepareStatement("SELECT * FROM register_student WHERE username = ?");
            psCheckUserExist.setString(1, username);
            resultSet = psCheckUserExist.executeQuery();

            if (resultSet.isBeforeFirst()){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username");
                alert.show();
            } else {

                String sql = "INSERT INTO register_student (fName, lName, studentId, username, password) VALUES (?,?,?,?,?)";
                psInsert = connection.prepareStatement(sql);
                psInsert.setString(1, fName);
                psInsert.setString(2, lName);
                psInsert.setString(3, staffId);
                psInsert.setString(4, username);
                psInsert.setString(5, password);

                int row = psInsert.executeUpdate();
                if (row > 0){
                    System.out.println("A new user was inserted successfully!");
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("A new user added successfully!");
                    alert.show();

                }

                //changeScene(event, "dashboard.fxml", "Dashboard", username);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psCheckUserExist != null){
                try {
                    psCheckUserExist.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psInsert != null){
                try {
                    psInsert.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }
    }

}
