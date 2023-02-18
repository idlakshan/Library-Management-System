package view;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Book;
import model.Details;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReportFormController implements Initializable {


    public TableView tblIssued;
    public AnchorPane root;
    public TableColumn colIssuedId;
    public TableColumn colMemberId;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colQty;
    public TableColumn colBookId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            loadAllDetails();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void BackOnAction(MouseEvent mouseEvent) {
        try {
            Parent parent= FXMLLoader.load(this.getClass().getResource("AdminDashboardForm.fxml"));
            Stage primaryStage= (Stage) root.getScene().getWindow();
            Scene scene=new Scene(parent);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadAllDetails() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                "root", "1234");
        PreparedStatement statement = connection.prepareStatement("select issued.Id,issued.memId,issued.date,issued.time,issueddetails.bookId,issueddetails.qtyForIssued from issued inner join issueddetails on issued.Id = issueddetails.issuedId;");
        ResultSet resultSet = statement.executeQuery();

        ArrayList<Details> details=new ArrayList<>();

        while (resultSet.next()){
            Details detail=new Details();

            detail.setId(resultSet.getString(1));
            detail.setMemId(resultSet.getString(2));
            detail.setDate(resultSet.getString(3));
            detail.setTime(resultSet.getString(4));
            detail.setBookId(resultSet.getString(5));
            detail.setQty(resultSet.getInt(6));

            details.add(detail);


            colIssuedId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colMemberId.setCellValueFactory(new PropertyValueFactory<>("memId"));
            colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
            colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
            colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));


            tblIssued.getColumns().setAll(colIssuedId,colMemberId,colDate,colTime,colBookId,colQty);
            tblIssued.setItems(FXCollections.observableArrayList(details));

        }

    }


}
