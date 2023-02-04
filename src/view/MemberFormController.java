package view;

import com.jfoenix.controls.JFXTextField;
import controller.MemberController;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberFormController {

    public JFXTextField txtSearchId;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtEmail;
    public TextField txtTel;


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
            clearTextFields();
        }
    }

    public void DeleteMember(ActionEvent event) {

        String deleteId = txtSearchId.getText();

        boolean isDeleted = MemberController.deleteMember(deleteId);

        if (isDeleted){
            new Alert(Alert.AlertType.INFORMATION,"Member has been Deleted").show();
            clearTextFields();
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
}
