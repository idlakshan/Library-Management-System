package view;

import com.jfoenix.controls.JFXTextField;
import controller.BookController;
import controller.MemberController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.Book;
import model.Member;
import view.tm.CartTm;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

public class IssueFormController {

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

    public void initialize(){
        colBookId.setCellValueFactory(new PropertyValueFactory<>("BookId"));
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memId"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colReturn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));



        loadTimeAndDate();
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
    }

    private void setBookData(String bookId) {
        Book book = BookController.searchBook(bookId);
        if (book==null){
            new Alert(Alert.AlertType.WARNING,"Empty Results");
        }else {
            txtBookName.setText(book.getName());
            txtQtyOnHand.setText(String.valueOf(book.getQty()));
        }

    }

    private void setMemberData(String memberId) throws SQLException, ClassNotFoundException {
        Member member = MemberController.searchMember(memberId);
        if (member==null){
            new Alert(Alert.AlertType.WARNING,"Empty Results");
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

    }

    public void Issued(ActionEvent event) {

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


}
