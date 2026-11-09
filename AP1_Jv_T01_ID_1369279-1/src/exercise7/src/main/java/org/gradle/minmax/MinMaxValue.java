package org.gradle.minmax;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.io.*;

public class MinMaxValue {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        scanner.close();

        float[] numbers = processOriginalFile(fileName);

        if (numbers != null) {
            outputAndWriteResultIntoFile(numbers);
        }

    }

    public static float[] processOriginalFile(String fileName) {

        InputStream inputStream = MinMaxValue.class.getClassLoader()
                .getResourceAsStream(fileName);

        if (inputStream == null) {
            System.out.println("Input error. File doesn't exist");
            return null;
        }

        try (Scanner fileScanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            if (!fileScanner.hasNextInt()) {
                System.out.println("Input error. Size <= 0.");
                return null;
            }

            int count = fileScanner.nextInt();
            if (count <= 0) {
                System.out.println("Input error. Size <= 0.");
                return null;
            }

            float[] numbers = new float[count];
            int numbersRead = 0;

            while (fileScanner.hasNext() && numbersRead < count) {
                if (fileScanner.hasNextFloat()) {
                    numbers[numbersRead] = fileScanner.nextFloat();
                    numbersRead++;
                } else {
                    fileScanner.next();
                }
            }

            if (numbersRead < count) {
                System.out.println("Input error. Insufficient number of items");
                return null;
            }

            return numbers;
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    public static void outputAndWriteResultIntoFile(float[] numbers) {
        float minValue = findMinimum(numbers);
        float maxValue = findMaximum(numbers);

        int n = numbers.length;
        System.out.println(n);
        for (float num: numbers) {
            System.out.printf("%s ", num);
        }

        System.out.println();
        System.out.println("Saving min and max values to file");
        try (PrintWriter writer = new PrintWriter("result.txt")) {
            writer.printf("%s %s", minValue, maxValue);
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    public static float findMinimum(float[] numbers) {
        float min = numbers[0];
        for (float number : numbers) {
            if (number < min) {
                min = number;
            }
        }
        return min;
    }

    public static float findMaximum(float[] numbers) {
        float max = numbers[0];
        for (float number : numbers) {
            if (number > max) {
                max = number;
            }
        }
        return max;
    }
}