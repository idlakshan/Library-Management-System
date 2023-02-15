package view;

import com.jfoenix.controls.JFXTextField;
import controller.UserController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UsersFormController implements Initializable {

    public AnchorPane root;
    public TableView tblUser;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colEmail;
    public TableColumn colPassword;
    public TableColumn colRole;
    public TextField txtId;
    public TextField txtName;
    public TextField txtEmail;
    public TextField txtPassword;
    public JFXTextField txtSearchId;
    public ComboBox<String> cmbRole;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbRole.setItems(FXCollections.observableArrayList("Admin", "User"));
        loadAllUsers();

    }

    public void AddUser(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String role = cmbRole.getValue();

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);

        boolean isAdded = UserController.addUser(user);

        if (isAdded) {
            new Alert(Alert.AlertType.INFORMATION, "successfully saved").show();
            loadAllUsers();
            clearTextFields();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "failed").show();
        }

    }

    public void UpdateUser(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String role = cmbRole.getValue();

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);

        boolean isUpdated = UserController.updateUser(user);


        if (isUpdated) {
            new Alert(Alert.AlertType.INFORMATION, "Successfully Updated").show();
            loadAllUsers();
            clearTextFields();
        }
    }

    public void DeleteUser(ActionEvent event) {
        String deleteId = txtSearchId.getText();

        boolean isDeleted = UserController.deleteUser(deleteId);

        if (isDeleted){
            new Alert(Alert.AlertType.INFORMATION,"Successfully Deleted").show();
            loadAllUsers();
            clearTextFields();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION,"There is no Member such as a given number").show();
        }
    }

    public void GetAll(ActionEvent event) {
        loadAllUsers();
    }

    public void searchUser(ActionEvent event) {
        String searchId = txtSearchId.getText();

        User user = UserController.searchUser(searchId);

        txtId.setText(user.getId());
        txtName.setText(user.getName());
        txtEmail.setText(user.getEmail());
        txtPassword.setText(user.getPassword());
        cmbRole.setValue(user.getRole());
    }

    public void loadAllUsers() {

        ArrayList<User> allUsers = UserController.getAllUsers();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));


        tblUser.getColumns().setAll(colId, colName, colEmail, colPassword, colRole);
        tblUser.setItems(FXCollections.observableArrayList(allUsers));

    }


    public void BackOnAction(MouseEvent mouseEvent) {
        try {
            Parent parent = FXMLLoader.load(this.getClass().getResource("AdminDashboardForm.fxml"));
            Stage primaryStage = (Stage) root.getScene().getWindow();
            Scene scene = new Scene(parent);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearTextFields(){
        txtId.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
        cmbRole.setValue("");
    }

}
