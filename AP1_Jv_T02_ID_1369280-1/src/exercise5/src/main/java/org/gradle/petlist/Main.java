package org.gradle.petlist;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.*;

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
        List<String> petTypes = Arrays.asList("cat", "dog");
        return petTypes.contains(petType);
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
        long programStartTime = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(pets.size());
        List<Future<?>> futures = new ArrayList<>();

        for (Animal pet : pets) {
            futures.add(executor.submit(() -> {
                long startTimeMillis = System.currentTimeMillis() - programStartTime;
                double startTime = startTimeMillis / 1000.0;

                pet.goToWalk();

                long endTimeMillis = System.currentTimeMillis() - programStartTime;
                double endTime = endTimeMillis / 1000.0;

                System.out.printf("%s, start time = %.2f, end time = %.2f%n",
                        pet, startTime, endTime);
            }));
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }
}