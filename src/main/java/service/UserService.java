package service;

import entity.User;
import repository.UserRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class UserService {

    Scanner scanner = new Scanner(System.in);
    private final UserRepository userRepository = new UserRepository();

    public UserService() throws SQLException {
    }

    public void register(User user) throws SQLException {
        int result = userRepository.save(user);
        if (result != 0)
            System.out.println(user.getUserName() + " successfully added to database");
        else
            System.out.println("ERROR!!");
    }

    public User login(String userName) throws SQLException {
        User user = userRepository.login(userName);
        return user;
    }

    public void changeUser(int id) throws SQLException {
        System.out.println("please enter your new userName: ");
        String userName = scanner.nextLine();
        System.out.println("please enter your new password: ");
        String password = scanner.nextLine();
        System.out.println("please enter your new signup_date: ");
        String signup_date = scanner.nextLine();
        int result = userRepository.update(userName,password,signup_date,id);
        if (result != 0)
            System.out.println("successfully update to database");
        else
            System.out.println("OOps!:(");
    }

    public void delete(int id) throws SQLException {
        int result = userRepository.delete(id);
        if (result != 0)
            System.out.println("successfully deleted from database");
        else
            System.out.println("OOps!:(");
    }

    public User load(int user_id) throws SQLException {
        User user = userRepository.load(user_id);
        return user;
    }

    public User[] loadAll() throws SQLException {
        User[] users = userRepository.loadAll();
        return users;
    }
}
