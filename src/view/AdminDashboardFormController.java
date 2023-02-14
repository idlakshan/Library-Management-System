package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardFormController {

    public AnchorPane root;


    public void BookLogin(ActionEvent event) {
        try {
            Parent parent= FXMLLoader.load(this.getClass().getResource("BookForm.fxml"));
            Stage primaryStage= (Stage) root.getScene().getWindow();
            Scene scene=new Scene(parent);
            primaryStage.setScene(scene);
            // primaryStage.setTitle("Login Form");
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backOnAction(MouseEvent mouseEvent) {

        try {
            Parent parent= FXMLLoader.load(this.getClass().getResource("LoginForm.fxml"));
            Stage primaryStage= (Stage) root.getScene().getWindow();
            Scene scene=new Scene(parent);
            primaryStage.setScene(scene);
            // primaryStage.setTitle("Login Form");
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
