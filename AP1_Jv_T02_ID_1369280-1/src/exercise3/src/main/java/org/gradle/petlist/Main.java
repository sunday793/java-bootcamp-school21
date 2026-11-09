package org.gradle.petlist;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

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

                    pets.add(createPet(petType, petName, petAge));
                }
                return pets;
            } catch (NumberFormatException e) {
                System.out.println("Could not parse a number. Please, try again");
            }
        }
    }

    public static boolean isSupportedPetType(String petType) {
        List<String> petTypes = Arrays.asList("cat", "dog", "hamster", "guinea");
        return petTypes.contains(petType);
    }

    public static String parsePetName(Scanner scanner) {
        return scanner.nextLine().trim();
    }

    public static Integer parsePetAge(Scanner scanner) {
        return Integer.parseInt(scanner.nextLine().trim());
    }

    public static Animal createPet(String petType, String petName, int petAge) {
        if (petType.equalsIgnoreCase("dog")) {
            return new Dog(petName, petAge);
        } else if (petType.equalsIgnoreCase("cat")) {
            return new Cat(petName, petAge);
        } else if (petType.equalsIgnoreCase("hamster")) {
            return new Hamster(petName, petAge);
        } else {
            return new GuineaPig(petName, petAge);
        }
    }

    public static void outputPetsList(List<Animal> pets) {
        pets.stream()
                .filter(pet -> pet instanceof Herbivore)
                .forEach(System.out::println);

        pets.stream()
                .filter(pet -> pet instanceof Omnivore)
                .forEach(System.out::println);
    }
}