package repository;

import connection.JdbcConnection;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

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

    public int update(String userName, String password, String signup_date ,int user_id) throws SQLException {
        String query = "UPDATE user1 SET userName = ?, password = ?, signup_date = ? WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2,password);
        preparedStatement.setString(3, signup_date);
        preparedStatement.setInt(4,user_id);
        int result = preparedStatement.executeUpdate();
        return result;
    }

    public int delete(int user_id) throws SQLException {
        String query = "DELETE FROM user1 WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,user_id);
        int result = preparedStatement.executeUpdate();
        return result;
    }

    public User load(int user_id) throws SQLException {
        String query = "SELECT * FROM user1 WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,user_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            User user = new User(
                    resultSet.getInt("user_id"),
                    resultSet.getString("userName"),
                    resultSet.getString("password"),
                    resultSet.getString("signup_date")
            );
            System.out.println(user);
            return user;
        }
        else
            return null;
    }

    public User[] loadAll() throws SQLException {
        String sql = "SELECT * FROM user1";
        PreparedStatement preparedStatement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.last();
        //System.out.println(resultSet.last());
        User[] users = new User[resultSet.getRow()];
        resultSet.beforeFirst();
        int counter = 0;
        while (resultSet.next()){
            users[counter]= new User(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            counter++;
        } //end while
        System.out.println(Arrays.toString(users));
        return users;

    }

    public void saveAll(User[]users) throws SQLException {
        String sql = "INSERT INTO user1(userName, password, signup_date) values(?,?,?)";
        for (int i = 0; i < users.length-1; i++) {
            sql = sql.concat(",(?,?,?)");
        }
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int i = 0;
        for (User u:users) {
            preparedStatement.setString(i+1,u.getUserName());
            preparedStatement.setString(i+2,u.getPassword());
            preparedStatement.setString(i+3, u.getSignup_date());
            i+=3;
        }
        preparedStatement.executeUpdate();
    }
}
