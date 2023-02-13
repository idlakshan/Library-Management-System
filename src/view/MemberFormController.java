package view;

import com.jfoenix.controls.JFXTextField;
import controller.MemberController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Member;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MemberFormController implements Initializable {

    public JFXTextField txtSearchId;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtEmail;
    public TextField txtTel;
    public TableView tblMember;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colEmail;
    public TableColumn colTel;
    public AnchorPane root;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllMembers();
       /* tblMember.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("cid"));
        tblCustomers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("town"));
        tblCustomers.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("salary"));*/
    }

    public void AddMember(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String tel = txtTel.getText();

        /*try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                    "root", "1234");
            PreparedStatement statement = connection.prepareStatement("insert into member values(?,?,?,?,?)");

            statement.setObject(1,id);
            statement.setObject(2,name);
            statement.setObject(3,address);
            statement.setObject(4,email);
            statement.setObject(5,tel);

            int i = statement.executeUpdate();

            if (i>0){
                new Alert(Alert.AlertType.INFORMATION,"Member has been saved").show();
                clearTextFields();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Save Member was failed").show();
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        Member member=new Member();
        member.setMid(id);
        member.setName(name);
        member.setAddress(address);
        member.setEmail(email);
        member.setTel(tel);

        boolean isAdded = MemberController.addMember(member);

        if (isAdded){
            new Alert(Alert.AlertType.INFORMATION,"Member has been saved").show();
            loadAllMembers();
            clearTextFields();
        }else {
            new Alert(Alert.AlertType.CONFIRMATION,"Save Member was failed").show();
        }

    }

    public void UpdateMember(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String tel = txtTel.getText();

       /* try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                    "root", "1234");

            PreparedStatement statement = connection.prepareStatement("update member set name=?,address=?,email=?,tel=? where mid=?");

            statement.setObject(1,name);
            statement.setObject(2,address);
            statement.setObject(3,email);
            statement.setObject(4,tel);
            statement.setObject(5,id);

            int i = statement.executeUpdate();

            if (i>0){
                new Alert(Alert.AlertType.INFORMATION,"Member has been Updated").show();
                clearTextFields();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION,"Update failed").show();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/

        Member member=new Member();
        member.setMid(id);
        member.setName(name);
        member.setAddress(address);
        member.setEmail(email);
        member.setTel(tel);

        boolean isUpdated = MemberController.updateMember(member);


        if (isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Member has been Updated").show();
            loadAllMembers();
            clearTextFields();
        }
    }

    public void DeleteMember(ActionEvent event) {

        String deleteId = txtSearchId.getText();

        boolean isDeleted = MemberController.deleteMember(deleteId);

        if (isDeleted){
            new Alert(Alert.AlertType.INFORMATION,"Member has been Deleted").show();
            clearTextFields();
            loadAllMembers();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION,"There is no Member such as a given number").show();
        }

       /* try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                    "root", "1234");

            PreparedStatement statement = connection.prepareStatement("delete from member where mid =?");
            statement.setObject(1,deleteId);

            int i = statement.executeUpdate();

            if (i>0){
                new Alert(Alert.AlertType.INFORMATION,"Member has been Deleted").show();
                clearTextFields();

            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"There is no Member such as a given number").show();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
    }

    public void GetAll(ActionEvent event) {
        loadAllMembers();
    }

    public void SearchMember(ActionEvent event) {

       /* String searchId = txtSearchId.getText();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                    "root", "1234");

            PreparedStatement statement = connection.prepareStatement("select * from member where mid=?");
            statement.setObject(1,searchId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){

              txtId.setText(resultSet.getString(1));
              txtName.setText(resultSet.getString(2));
              txtAddress.setText(resultSet.getString(3));
              txtEmail.setText(resultSet.getString(4));
              txtTel.setText(resultSet.getString(5));

              txtSearchId.clear();

            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"There is no Member such as a given number").show();
            }



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        String searchId = txtSearchId.getText();

        Member member = MemberController.searchMember(searchId);

        txtId.setText(member.getMid());
        txtName.setText(member.getName());
        txtAddress.setText(member.getAddress());
        txtEmail.setText(member.getEmail());
        txtTel.setText(member.getTel());

    }


    public void clearTextFields(){
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtTel.setText("");
    }

    public void loadAllMembers(){

        ArrayList<Member> allMembers = MemberController.getAllMembers();

        colId.setCellValueFactory(new PropertyValueFactory<>("mid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));


        tblMember.getColumns().setAll(colId,colName,colAddress,colEmail,colTel);
        tblMember.setItems(FXCollections.observableArrayList(allMembers));

    }


    public void BackOnAction(MouseEvent mouseEvent) {
        try {
            Parent parent= FXMLLoader.load(this.getClass().getResource("UserHomeForm.fxml"));
            Stage primaryStage= (Stage) root.getScene().getWindow();
            Scene scene=new Scene(parent);
            primaryStage.setScene(scene);
            //primaryStage.setTitle("Login Form");
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
