package controller;

import model.User;

import java.sql.*;

public class LoginController {
    public static String login(User user) throws ClassNotFoundException, SQLException {
        String userName = user.getuName();
        String password = user.getuPassword();


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                "root", "1234");

        PreparedStatement statement = connection.prepareStatement("select * from users where  uName=? and uPassword=? ");

        statement.setObject(1, userName);
        statement.setObject(2, password);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getString(5);
        } else {
            return "";
        }
    }

}
