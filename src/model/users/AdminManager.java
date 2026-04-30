package model.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminManager {

    private Admin admin;
    private Scanner scanner = new Scanner(System.in);


    public void register() {
        if (admin != null) {
            System.out.println("Admin already exists!");
            return;
        }

        System.out.println("Enter username");
        String username = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();
        System.out.println("Enter secret question");
        String secretQuestion = scanner.nextLine();
        System.out.println("Enter secret answer");
        String secretAnswer = scanner.nextLine();

        admin = new Admin(username, password, secretQuestion, secretAnswer);
    }



    public void login() {
        if (admin == null) {
            System.out.println("No admin registered yet!");
            return;
        }

        System.out.println("Enter username");
        String username = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();

        if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
            System.out.println(admin.getUsername() + " logged in");
        } else {
            System.out.println("Invalid username or password");
        }
    }

    public void forgetPassword() {
        if (admin == null) {
            System.out.println("No admin registered!");
            return;
        }

        System.out.println("Enter username");
        String username = scanner.nextLine();

        if (admin.getUsername().equals(username)) {
            System.out.println("Answer for: " + admin.getSecretQuestion());
            String answer = scanner.nextLine();

            if (answer.equals(admin.getSecretAnswer())) {
                System.out.println("Enter new password");
                String newPassword = scanner.nextLine();
                admin.setPassword(newPassword);
                System.out.println("Password updated");
            } else {
                System.out.println("Invalid answer");
            }
        }
    }
}
