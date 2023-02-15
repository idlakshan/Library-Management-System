package view;

import controller.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {

    public TextField txtUserName;
    public PasswordField txtPassword;
    public AnchorPane root;


    public void Login(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        User user=new User(txtUserName.getText(),txtPassword.getText());
        String role= LoginController.login(user);
        System.out.println(role);

        if(role.equals("admin")||role.equals("Admin")){
            try {
                Parent parent= FXMLLoader.load(this.getClass().getResource("AdminDashboardForm.fxml"));
                Stage primaryStage= (Stage) root.getScene().getWindow();
                Scene scene=new Scene(parent);
                primaryStage.setScene(scene);
                // primaryStage.setTitle("Login Form");
                primaryStage.centerOnScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }else if(role.equals("user")||role.equals("User")){
            try {
                Parent parent= FXMLLoader.load(this.getClass().getResource("UserDashboardForm.fxml"));
                Stage primaryStage= (Stage) root.getScene().getWindow();
                Scene scene=new Scene(parent);
                primaryStage.setScene(scene);
                // primaryStage.setTitle("Login Form");
                primaryStage.centerOnScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            new Alert(Alert.AlertType.WARNING,"Something went wrong Please try again..").show();
            txtUserName.clear();
            txtPassword.clear();
        }



    }
}
