package controller;

import model.Issued;
import model.IssuedDetails;

import java.sql.*;
import java.util.ArrayList;

public class IssuedController {

    public static String getIssuedId() throws ClassNotFoundException, SQLException {


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                "root", "1234");
        PreparedStatement statement = connection.prepareStatement("select id from issued order by id desc limit 1 ");

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int tempId = Integer.parseInt(resultSet.getString(1).split("-")[1]);

            tempId = tempId + 1;

            if (tempId < 10) {
                return "O-00" + tempId;
            } else if (tempId < 99) {
                return "O-0" + tempId;
            } else {
                return "O-" + tempId;
            }

        } else {
            return "I-001";
        }
    }


    public static boolean issuedBooks(Issued issued){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                    "root", "1234");

            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement("insert into issued values (?,?,?,?)");
            statement.setObject(1, issued.getId());
            statement.setObject(2, issued.getMemId());
            statement.setObject(3, issued.getIssuedDate());
            statement.setObject(4, issued.getIssuedTime());

            if (statement.executeUpdate() > 0) {
               // boolean b = saveIssuedDetails(issued.getId(), issued.getDetails());
                if (saveIssuedDetails(issued.getId(), issued.getDetails())) {

                 connection.commit();
                 connection.setAutoCommit(true);
                    return true;

                } else {
            connection.rollback();
            connection.setAutoCommit(true);
                    return false;
                }

            } else {
             connection.rollback();
             connection.setAutoCommit(true);
                return false;

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public static boolean saveIssuedDetails(String issuedId, ArrayList<IssuedDetails> details) throws ClassNotFoundException, SQLException {

        for (IssuedDetails tempDetails : details
        ) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                    "root", "1234");
            PreparedStatement statement = connection.prepareStatement("insert into issueddetails values (?,?,?)");
            statement.setObject(1, issuedId);
            statement.setObject(2, tempDetails.getBookId());
            statement.setObject(3, tempDetails.getQtyForIssued());

            if (statement.executeUpdate() > 0) {
                if (updateQty(tempDetails.getBookId(), tempDetails.getQtyForIssued())) {

                } else {
                    return false;
                }

            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(String bookId, int qty) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                "root", "1234");
        PreparedStatement statement = connection.prepareStatement("update book set qty = (qty-" + qty + ") where bId='" + bookId + "'");
        return statement.executeUpdate() > 0;
    }
}
