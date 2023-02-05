package controller;

import javafx.scene.control.Alert;
import model.Book;

import java.sql.*;
import java.util.ArrayList;

public class BookController {

    public static boolean addBook(Book book){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                    "root", "1234");

            PreparedStatement statement = connection.prepareStatement("insert into book values(?,?,?,?,?)");

            statement.setObject(1,book.getBid());
            statement.setObject(2,book.getName());
            statement.setObject(3,book.getAuthor());
            statement.setObject(4,book.getQty());
            statement.setObject(5,book.getPrice());

            int i = statement.executeUpdate();

            if (i>0){
                return true;
            }else {
                return false;
            }



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public static boolean deleteBook(String id){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                    "root", "1234");
            PreparedStatement statement = connection.prepareStatement("delete from book where bid=?");

            statement.setObject(1,id);

            int i = statement.executeUpdate();

            if (i>0){
                return true;
            }else {
                return false;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public static boolean updateBook(Book book){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                    "root", "1234");
            PreparedStatement statement = connection.prepareStatement("update book set name=?,author=?,qty=?,price=? where bid=?");

            statement.setObject(1,book.getName());
            statement.setObject(2,book.getAuthor());
            statement.setObject(3,book.getQty());
            statement.setObject(4,book.getPrice());
            statement.setObject(5,book.getBid());

            int i = statement.executeUpdate();

            if(i>0){
                return true;
            }else {
                return false;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }


    public static Book searchBook(String id){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                    "root", "1234");
            PreparedStatement statement = connection.prepareStatement("select * from book where bid=?");

            statement.setObject(1,id);

            ResultSet resultSet = statement.executeQuery();

                Book book=new Book();

            if(resultSet.next()){
                book.setBid(resultSet.getString(1));
                book.setName(resultSet.getString(2));
                book.setAuthor(resultSet.getString(3));
                book.setQty(Integer.parseInt(resultSet.getString(4)));
                book.setPrice(Double.parseDouble(resultSet.getString(5)));

                return book;
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"There is no book such as a given Id").show();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    public static ArrayList<Book> getAllBooks(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                    "root", "1234");
            PreparedStatement statement = connection.prepareStatement("select * from book");

            ResultSet resultSet = statement.executeQuery();

            ArrayList<Book> books=new ArrayList<>();

            while (resultSet.next()){
              Book book=new Book();

              book.setBid(resultSet.getString(1));
              book.setName(resultSet.getString(2));
              book.setAuthor(resultSet.getString(3));
              book.setQty(resultSet.getInt(4));
              book.setPrice(resultSet.getDouble(5));

                books.add(book);
            }
          return books;



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }





        return null;
    }
}
