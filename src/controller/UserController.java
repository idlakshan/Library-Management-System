package controller;


import javafx.scene.control.Alert;
import model.Member;
import model.User;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.util.ArrayList;

public class UserController {

    public static boolean addUser(User user){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                    "root", "1234");

            PreparedStatement statement = connection.prepareStatement("insert into users values(?,?,?,?,?)");

            statement.setObject(1,user.getId());
            statement.setObject(2,user.getName());
            statement.setObject(3,user.getEmail());
            statement.setObject(4,user.getPassword());
            statement.setObject(5,user.getRole());

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
    public static boolean updateUser(User user){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                    "root", "1234");

            PreparedStatement statement = connection.prepareStatement("update users set uName=?,uEmail=?,uPassword=?,uRole=? where uId=?");

            statement.setObject(1,user.getName());
            statement.setObject(2,user.getEmail());
            statement.setObject(3,user.getPassword());
            statement.setObject(4,user.getRole());
            statement.setObject(5,user.getId());

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
    public static User searchUser(String searchId){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                    "root", "1234");
            PreparedStatement statement = connection.prepareStatement("select * from users where uId=?");

            statement.setObject(1,searchId);
            ResultSet resultSet = statement.executeQuery();

            User user=new User();

            if (resultSet.next()){
                user.setId(resultSet.getString(1));
                user.setName(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                user.setRole(resultSet.getString(5));

                return user;

            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"There is no User such as a given number").show();
            }



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deleteUser(String deleteId){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                    "root", "1234");
            PreparedStatement statement = connection.prepareStatement("delete from users where uId =?");

            statement.setObject(1,deleteId);

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


    public static ArrayList<User> getAllUsers(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                    "root", "1234");
            PreparedStatement statement = connection.prepareStatement("select * from users");

            ResultSet resultSet = statement.executeQuery();

            ArrayList<User> users=new ArrayList<>();

            while (resultSet.next()){
                User user=new User();
                user.setId(resultSet.getString(1));
                user.setName(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                user.setRole(resultSet.getString(5));

                users.add(user);

            }

           return users;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }


}
