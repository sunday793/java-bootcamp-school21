package org.gradle.petlist;
import java.util.Objects;
import java.util.Scanner;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Animal> pets = readPets(scanner);
        outputPetsList(pets);
    }

    public static List<Animal> readPets(Scanner scanner) {
        int numberOfPets = readNumberOfPets(scanner);

        return IntStream.range(0, numberOfPets)
                .mapToObj(i -> readSinglePet(scanner))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static int readNumberOfPets(Scanner scanner) {
        try {
            int numberOfPets = Integer.parseInt(scanner.nextLine().trim());
            if (numberOfPets <= 0) {
                System.out.println("Input error. Number of pets <= 0");
                return readNumberOfPets(scanner);
            }
            return numberOfPets;
        } catch (NumberFormatException e) {
            System.out.println("Could not parse a number. Please, try again");
            return readNumberOfPets(scanner);
        }
    }

    public static Animal readSinglePet(Scanner scanner) {
        String petType = scanner.nextLine().trim();

        if (!isSupportedPetType(petType)) {
            System.out.println("Incorrect input. Unsupported pet type");
            return null;
        }

        String petName = parsePetName(scanner);
        int petAge = parsePetAge(scanner);

        if (petAge <= 0) {
            System.out.println("Incorrect input. Age <= 0");
            return null;
        }
        return createPet(petType, petName, petAge);
    }

    public static boolean isSupportedPetType(String petType) {
        return List.of("cat", "dog").contains(petType);
    }

    public static String parsePetName(Scanner scanner) {
        return scanner.nextLine().trim();
    }

    public static Integer parsePetAge(Scanner scanner) {
        return Integer.parseInt(scanner.nextLine().trim());
    }

    public static Animal createPet(String petType, String petName, int petAge) {
        return switch (petType.toLowerCase()) {
            case "dog" -> new Dog(petName, petAge);
            case "cat" -> new Cat(petName, petAge);
            default -> null;
        };
    }

    public static void outputPetsList(List<Animal> pets) {
        pets.stream()
                .map(pet -> {
                    if (pet.getPetAge() > 10) {
                        String petType = pet.getClass().getSimpleName().toLowerCase();
                        return createPet(petType, pet.getPetName(), pet.getPetAge() + 1);
                    }
                    return pet;
                })
                .forEach(System.out::println);
    }
}