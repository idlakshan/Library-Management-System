package view;

import com.jfoenix.controls.JFXTextField;
import controller.BookController;
import controller.IssuedController;
import controller.MemberController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Book;
import model.Issued;
import model.IssuedDetails;
import model.Member;
import view.tm.CartTm;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

public class IssuedFormController {

    public AnchorPane root;
    public TableView tblIssued;
    public TableColumn colReturn;
    public ComboBox<String> cmbMemId;
    public ComboBox<String> cmbBookId;
    public DatePicker dateReturn;
    public TableColumn colBookId;
    public TableColumn colMemberId;
    public TableColumn colBookName;
    public TableColumn colQty;
    public Label lblIssuedID;
    public TextField txtMemberName;
    public TextField txtBookName;
    public TextField txtQtyOnHand;
    public TextField txtQty;
    public Label lblTime;
    public Label lblDate;

    int cartSelectedRow=-1;

    public void initialize(){
        colBookId.setCellValueFactory(new PropertyValueFactory<>("BookId"));
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memId"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colReturn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));



        loadTimeAndDate();
        setIssuedId();
        try {
            loadAllMemberIds();
            loadAllBookIds();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbMemId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            try {
                setMemberData(newValue);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } );

        cmbBookId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{

                setBookData(newValue);
        } );

        tblIssued.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
           cartSelectedRow= (int) newValue;


        });


    }

    private void setIssuedId(){
        try {
            lblIssuedID.setText(IssuedController.getIssuedId());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setBookData(String bookId) {
        Book book = BookController.searchBook(bookId);
        if (book==null){
            new Alert(Alert.AlertType.WARNING,"Empty Results").show();
        }else {
            txtBookName.setText(book.getName());
            txtQtyOnHand.setText(String.valueOf(book.getQty()));
        }

    }

    private void setMemberData(String memberId) throws SQLException, ClassNotFoundException {
        Member member = MemberController.searchMember(memberId);
        if (member==null){
            new Alert(Alert.AlertType.WARNING,"Empty Results").show();
        }else {
               txtMemberName.setText(member.getName());
        }

    }

    private void loadAllBookIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> bookIds = new BookController().getBookIds();
        cmbBookId.getItems().addAll(bookIds);
    }

    private void loadAllMemberIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> memberIds = new MemberController().getMemberIds();
        cmbMemId.getItems().addAll(memberIds);

    }

    private void loadTimeAndDate() {
        Date date=new Date();
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline timeline=new Timeline(new KeyFrame(Duration.ZERO, event -> {
            LocalTime currentTime=LocalTime.now();
            lblTime.setText(
                    currentTime.getHour()+" : "+ currentTime.getMinute()+" : "+ currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void BackOnAction(MouseEvent mouseEvent) {
        try {
            Parent parent= FXMLLoader.load(this.getClass().getResource("UserDashboardForm.fxml"));
            Stage primaryStage= (Stage) root.getScene().getWindow();
            Scene scene=new Scene(parent);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void Issued(ActionEvent event) {
        ArrayList<IssuedDetails> books=new ArrayList<>();
        for (CartTm tempTm:obList
             ) {
            books.add(new IssuedDetails(
                    tempTm.getBookId(),
                    tempTm.getQty()
            ));
        }

        Issued issued = new Issued(
                lblIssuedID.getText(),
                cmbMemId.getValue(),
                lblDate.getText(),
                lblTime.getText(),
                books

        );
        if(IssuedController.issuedBooks(issued)){
            new Alert(Alert.AlertType.CONFIRMATION,"Success").show();
            setIssuedId();
        }else {
            new Alert(Alert.AlertType.WARNING,"Failed").show();
        }


    }

    ObservableList<CartTm> obList = FXCollections.observableArrayList();
    public void AddCart(ActionEvent event) {
        String bookName = txtBookName.getText();
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        int qty = Integer.parseInt(txtQty.getText());
        String date= String.valueOf(dateReturn.getValue());

        if (qtyOnHand<qty){
            new Alert(Alert.AlertType.ERROR,"Invalid input").show();
            return;
        }

        CartTm cartTm=new CartTm(cmbBookId.getValue(),cmbMemId.getValue(),bookName,qty,date);

        int rowNumber=isExits(cartTm);


       if (rowNumber==-1){
        obList.add(cartTm);
       }else {
           CartTm temp=obList.get(rowNumber);
           CartTm newTm=new CartTm(
                   temp.getBookId(),
                   temp.getMemId(),
                   temp.getBookName(),
                   temp.getQty()+qty,
                   temp.getReturnDate()

           );

           if (qtyOnHand<temp.getQty()){
               new Alert(Alert.AlertType.ERROR,"Invalid input").show();
               return;
           }
           obList.remove(rowNumber);
           obList.add(newTm);

       }

           tblIssued.setItems(obList);
    }
    public int isExits(CartTm tm){

        for (int i = 0; i <obList.size() ; i++) {
            if (tm.getBookId().equals(obList.get(i).getBookId())){
                  return i;
            }
        }
        return -1;

    }


    public void clear(ActionEvent event) {
        if(cartSelectedRow==-1){
            new Alert(Alert.AlertType.WARNING,"Please Select a Row").show();
        }else {
            obList.remove(cartSelectedRow);
            tblIssued.refresh();
            new Alert(Alert.AlertType.CONFIRMATION,"Done").show();
            clearTextFields();
        }
    }

    void clearTextFields(){
        //cmbBookId.getSelectionModel().clearSelection();
        //cmbMemId.getSelectionModel().clearSelection();
        txtBookName.clear();
        txtQty.clear();
        txtQtyOnHand.clear();
        txtMemberName.clear();
        dateReturn.setValue(LocalDate.now());
    }
}
