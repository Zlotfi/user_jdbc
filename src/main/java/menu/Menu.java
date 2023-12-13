package menu;

import entity.User;
import service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final UserService userService = new UserService();

    public Menu() throws SQLException {
    }

    public void publicMenu() throws SQLException {
        System.out.println("***Welcome***");
        System.out.println("1- Sign in");
        System.out.println("2- Sign up");
        System.out.println("3- saveAll users");
        System.out.println("4- Exit");
        System.out.println("Enter your select: ");
        int select = scanner.nextInt();
        scanner.nextLine();
        switch (select){
            case 1 -> enter();
            case 2 -> register();
            case 3 -> {User[] user = new User[3];
                for (int i = 0; i < 3; i++) {
                    user[i]=new User(null,"zlot","123","12-22-10");
                }
                userService.saveAll(user);}
            case 4 -> System.out.println("Exit");
            default -> System.out.println("error");
        }
    }

    public void register() throws SQLException {
        System.out.println("Enter your userName: ");
        String userName = scanner.nextLine();

        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        System.out.println("Enter your signup_date: ");
        String signup_date = scanner.nextLine();

        User user = new User(null, userName, password, signup_date);
        userService.register(user);
    }

    public void enter() throws SQLException {
        System.out.println("Enter your userName: ");
        String userName = scanner.nextLine();

        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        User user = userService.login(userName);
        if ((user == null) && !user.getPassword().equals(password))
            System.out.println("you enter a userName and password incorrect");
        else{
            boolean isTrue = true;
            while (isTrue) {
                System.out.println("=======================================");
                System.out.println("1- update user: ");
                System.out.println("2- delete yourself: ");
                System.out.println("3- load user: ");
                System.out.println("4- loadAll users: ");
                System.out.println("5- Exit");
                System.out.println("enter your number: ");
                int number = scanner.nextInt();
                scanner.nextLine();
                switch (number) {
                    case 1 -> userService.changeUser(user.getUser_id());
                    case 2 -> userService.delete(user.getUser_id());
                    case 3 -> userService.load(user.getUser_id());
                    case 4 -> userService.loadAll();
                    case 5 -> isTrue = false;
                    default -> System.out.println("ERROR");
                }
            }
        }
    }
}
