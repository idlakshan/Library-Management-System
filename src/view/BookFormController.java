package view;

import com.jfoenix.controls.JFXTextField;
import controller.BookController;
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
import model.Book;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookFormController implements Initializable {

    public AnchorPane root;
    public TableView tblBook;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAuthor;
    public TableColumn colQty;
    public TableColumn colPrice;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAuthor;
    public TextField txtQty;
    public TextField txtPrice;
    public JFXTextField txtSearchId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllBooks();
    }

    public void AddBook(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String author = txtAuthor.getText();
        int qty = Integer.parseInt(txtQty.getText());
        Double price = Double.valueOf(txtPrice.getText());

        Book book=new Book();
        book.setBid(id);
        book.setName(name);
        book.setAuthor(author);
        book.setQty(qty);
        book.setPrice(price);

        boolean isAdded=BookController.addBook(book);

        if (isAdded){
            new Alert(Alert.AlertType.INFORMATION,"Book has been saved").show();
            loadAllBooks();
            clearTextFields();
        }else {
            new Alert(Alert.AlertType.CONFIRMATION,"Save Book was failed").show();
        }
    }

    public void UpdateBook(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String author = txtAuthor.getText();
        int qty = Integer.parseInt(txtQty.getText());
        Double price = Double.valueOf(txtPrice.getText());

        Book book=new Book();
        book.setBid(id);
        book.setName(name);
        book.setAuthor(author);
        book.setQty(qty);
        book.setPrice(price);

        boolean isUpdated = BookController.updateBook(book);

        if (isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Book has been updated").show();
            loadAllBooks();
            clearTextFields();
        }else {
            new Alert(Alert.AlertType.CONFIRMATION,"Update Book was failed").show();        }
    }

    public void DeleteBook(ActionEvent event) {
        String id = txtId.getText();

        boolean isDeleted = BookController.deleteBook(id);

        if (isDeleted){
            new Alert(Alert.AlertType.INFORMATION,"Book has been deleted").show();
            clearTextFields();
            loadAllBooks();
        }else {
            new Alert(Alert.AlertType.CONFIRMATION,"There is no Book such as a given number").show();
        }


    }

    public void GetAll(ActionEvent event) {
      loadAllBooks();
    }

    public void SearchBook(ActionEvent event) {
        String id= txtSearchId.getText();

        Book book = BookController.searchBook(id);

        txtId.setText(book.getBid());
        txtName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtQty.setText(String.valueOf(book.getQty()));
        txtPrice.setText(String.valueOf(book.getPrice()));

        txtSearchId.clear();

    }

    public void BackOnAction(MouseEvent mouseEvent) {
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
    }
    public void clearTextFields(){
        txtId.setText("");
        txtName.setText("");
        txtAuthor.setText("");
        txtQty.setText("");
        txtPrice.setText("");

    }
    public void loadAllBooks(){

        ArrayList<Book> books = BookController.getAllBooks();

        colId.setCellValueFactory(new PropertyValueFactory<>("bid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


        tblBook.getColumns().setAll(colId,colName,colAuthor,colQty,colPrice);
        tblBook.setItems(FXCollections.observableArrayList(books));

    }


}
