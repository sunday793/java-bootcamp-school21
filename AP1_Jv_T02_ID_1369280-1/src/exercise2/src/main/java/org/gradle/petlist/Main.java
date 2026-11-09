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

                    if (!isSupportedPetType(petType)) {
                        System.out.println("Incorrect input. Unsupported pet type");
                        continue;
                    }

                    String petName = parsePetName(scanner);
                    int petAge = parsePetAge(scanner);

                    if (petAge <= 0) {
                        System.out.println("Incorrect input. Age <= 0");
                        continue;
                    }

                    Double petWeight = parsePetWeight(scanner);

                    if (petWeight <= 0) {
                        System.out.println("Incorrect input. Mass <= 0");
                        continue;
                    }

                    pets.add(createPet(petType, petName, petAge, petWeight));
                }
                return pets;
            } catch (NumberFormatException e) {
                System.out.println("Could not parse a number. Please, try again");
            }
        }
    }

    public static boolean isSupportedPetType(String petType) {
        return petType.equalsIgnoreCase("cat") || petType.equalsIgnoreCase("dog");
    }

    public static String parsePetName(Scanner scanner) {
        return scanner.nextLine().trim();
    }

    public static Integer parsePetAge(Scanner scanner) {
        return Integer.parseInt(scanner.nextLine().trim());
    }

    public static Double parsePetWeight(Scanner scanner) {
        return Double.parseDouble(scanner.nextLine().trim());
    }

    public static Animal createPet(String petType, String petName, int petAge, Double petWeight) {
        if (petType.equalsIgnoreCase("dog")) {
            return new Dog(petName, petAge, petWeight);
        } else {
            return new Cat(petName, petAge, petWeight);
        }
    }

    public static void outputPetsList(List<Animal> pets) {
        for (Animal pet: pets) {
            System.out.println(pet);
        }
    }
}