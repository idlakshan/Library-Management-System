package controller;

import javafx.scene.control.Alert;
import model.Member;

import java.sql.*;
import java.util.ArrayList;

public class MemberController {

    public static boolean addMember(Member member){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                    "root", "1234");

            PreparedStatement statement = connection.prepareStatement("insert into member values(?,?,?,?,?)");

            statement.setObject(1,member.getMid());
            statement.setObject(2,member.getName());
            statement.setObject(3,member.getAddress());
            statement.setObject(4,member.getEmail());
            statement.setObject(5,member.getTel());

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

    public static boolean updateMember(Member member){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                    "root", "1234");

            PreparedStatement statement = connection.prepareStatement("update member set mName=?,address=?,email=?,tel=? where mId=?");

            statement.setObject(1,member.getName());
            statement.setObject(2,member.getAddress());
            statement.setObject(3,member.getEmail());
            statement.setObject(4,member.getTel());
            statement.setObject(5,member.getMid());

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

    public static boolean deleteMember(String deleteId){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                    "root", "1234");
            PreparedStatement statement = connection.prepareStatement("delete from member where mId =?");

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

    public static Member searchMember(String searchId){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                    "root", "1234");
            PreparedStatement statement = connection.prepareStatement("select * from member where mId=?");

            statement.setObject(1,searchId);
            ResultSet resultSet = statement.executeQuery();

            Member member=new Member();

            if (resultSet.next()){
                member.setMid(resultSet.getString(1));
                member.setName(resultSet.getString(2));
                member.setAddress(resultSet.getString(3));
                member.setEmail(resultSet.getString(4));
                member.setTel(resultSet.getString(5));

                return member;

            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"There is no Member such as a given number").show();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return null;
    }

    public static ArrayList<Member> getAllMembers(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                    "root", "1234");
            PreparedStatement statement = connection.prepareStatement("select * from member");

            ResultSet resultSet = statement.executeQuery();

            ArrayList<Member> members=new ArrayList<>();

            while (resultSet.next()){
                Member member=new Member();

                member.setMid(resultSet.getString(1));
                member.setName(resultSet.getString(2));
                member.setAddress(resultSet.getString(3));
                member.setEmail(resultSet.getString(4));
                member.setTel(resultSet.getString(5));

                members.add(member);
            }

              return members;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return null;
    }

}
