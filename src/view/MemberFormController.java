package view;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.*;

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

        try {
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
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Save Member was failed").show();
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void UpdateMember(ActionEvent event) {

    }

    public void DeleteMember(ActionEvent event) {

    }

    public void GetAll(ActionEvent event) {

    }

    public void SearchMember(ActionEvent event) {
        String searchId = txtSearchId.getText();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                    "root", "1234");

            PreparedStatement statement = connection.prepareStatement("select * from member where mid=?");
            statement.setObject(1,searchId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                new Alert(Alert.AlertType.INFORMATION,"Member search successfully done").show();

              txtId.setText(resultSet.getString(1));
              txtName.setText(resultSet.getString(2));
              txtAddress.setText(resultSet.getString(3));
              txtEmail.setText(resultSet.getString(4));
              txtTel.setText(resultSet.getString(5));
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"There is no Member").show();
            }



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
