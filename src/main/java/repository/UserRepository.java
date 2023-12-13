package repository;

import connection.JdbcConnection;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    JdbcConnection jdbcConnection = new JdbcConnection();
    Connection connection = jdbcConnection.getConnection();

    public UserRepository() throws SQLException {
    }

    public int save(User user) throws SQLException {
        String add = "INSERT INTO user1 (userName, password, signup_date) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(add);
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getSignup_date());
        int result = preparedStatement.executeUpdate();
        return result;
    }

    public User login(String userName) throws SQLException {
        String loginQuery = "SELECT * FROM user1 WHERE userName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(loginQuery);
        preparedStatement.setString(1,userName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            User user = new User(
                    resultSet.getInt("user_id"),
                    resultSet.getString("userName"),
                    resultSet.getString("password"),
                    resultSet.getString("signup_date")
            );
            return user;
        }
        else
            return null;
    }
}
