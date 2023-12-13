package service;

import entity.User;
import repository.UserRepository;

import java.sql.SQLException;

public class UserService {

    private final UserRepository userRepository = new UserRepository();

    public UserService() throws SQLException {
    }

    public void register(User user) throws SQLException {
        int result = userRepository.save(user);
        if (result != 0)
            System.out.println(user.getUserName() + "successfully added to database");
        else
            System.out.println("ERROR!!");
    }

    public User login(String userName) throws SQLException {
        User user = userRepository.login(userName);
        return user;
    }
}
