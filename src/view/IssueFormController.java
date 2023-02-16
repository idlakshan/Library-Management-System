package view;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class IssueFormController {

    public AnchorPane root;
    public TableView tblIssued;
    public TableColumn colReturn;
    public ComboBox cmbMemId;
    public ComboBox cmbBookId;
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
        loadTimeAndDate();
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


    public void AddCart(ActionEvent event) {

    }
}
