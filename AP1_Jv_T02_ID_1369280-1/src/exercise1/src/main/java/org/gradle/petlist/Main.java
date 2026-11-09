package org.gradle.petlist;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Animal> pets = readPets(scanner);
        outputPetsList(pets);
    }

    public static List<Animal> readPets(Scanner scanner) {
        List<Animal> pets = new ArrayList<>();

        while(true) {
            try {
                int numberOfPets = Integer.parseInt(scanner.nextLine().trim());
                if (numberOfPets <= 0) {
                    System.out.println("Input error. Number of pets <= 0");
                    continue;
                }

                for (int i = 0; i < numberOfPets; i ++) {
                    String petType = scanner.nextLine().trim();

                    if (petType.equalsIgnoreCase("dog")) {
                        String petName = parsePetName(scanner);
                        int petAge = parsePetAge(scanner);
                        if (petAge <= 0) {
                            System.out.println("Incorrect input. Age <= 0");
                        } else {
                            pets.add(new Dog(petName, petAge));
                        }
                    }
                    else if (petType.equalsIgnoreCase("cat")) {
                        String petName = parsePetName(scanner);
                        int petAge = parsePetAge(scanner);
                        if (petAge <= 0) {
                            System.out.println("Incorrect input. Age <= 0");
                        } else {
                            pets.add(new Cat(petName, petAge));
                        }
                    } else {
                        System.out.println("Incorrect input. Unsupported pet type");
                    }
                }
                return pets;
            } catch (NumberFormatException e) {
                System.out.println("Could not parse a number. Please, try again");
            }
        }
    }

    public static String parsePetName(Scanner scanner) {
        return scanner.nextLine().trim();
    }

    public static Integer parsePetAge(Scanner scanner) {
        return Integer.parseInt(scanner.nextLine().trim());
    }

    public static void outputPetsList(List<Animal> pets) {
        for (Animal pet: pets) {
            System.out.println(pet);
        }
    }
}