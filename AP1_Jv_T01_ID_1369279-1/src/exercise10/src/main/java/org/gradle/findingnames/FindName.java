package org.gradle.findingnames;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class FindName {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<User> users = readAllUsers(scanner);
        printAdultUsers(users);

        scanner.close();
    }

    private static int readNumberOfUsers(Scanner scanner) {
        while(true) {
            try {
                int numberOfUsers = Integer.parseInt(scanner.nextLine().trim());
                if (numberOfUsers <= 0) {
                    System.out.println("Invalid input. Number of users <= 0");
                    continue;
                }
                return numberOfUsers;
            } catch (NumberFormatException e) {
                System.out.println("Could not parse a number. Please try again");
            }
        }
    }

    private static User readUser(Scanner scanner) {
        String name = scanner.nextLine().trim();
        Integer age = parseAge(scanner.nextLine().trim());

        if (age == null) {
            System.out.println("Could not parse a number. Please try again");
            return null;
        }

        if (age <= 0) {
            System.out.println("Incorrect input. Age <= 0");
            return null;
        }
        return new User(name, age);
    }

    private static Integer parseAge(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static List<User> readAllUsers(Scanner scanner) {
        List <User> users = new ArrayList<>();
        int numberOfUsers = readNumberOfUsers(scanner);

        while (users.size() < numberOfUsers) {
            User user = readUser(scanner);
            if (user != null) {
                users.add(user);
            }
        }
        return users;
    }

    private static void printAdultUsers(List<User> users) {
        String adultNames = users.stream()
                .filter(user -> user.getAge() >= 18)
                .map(User::getName)
                .collect(Collectors.joining(", "));

        if (!adultNames.isEmpty()) {
            System.out.println(adultNames);
        }
    }

}