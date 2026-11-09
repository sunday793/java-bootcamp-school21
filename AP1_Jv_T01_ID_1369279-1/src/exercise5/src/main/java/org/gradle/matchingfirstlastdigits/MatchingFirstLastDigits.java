package org.gradle.matchingfirstlastdigits;
import java.util.Scanner;

public class MatchingFirstLastDigits {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int[] n = readData(in);

        int[] result = findMatchingFirstLastDigit(n);

        if (result.length > 0) {
            outputResult(result);
        }

        in.close();
    }

    public static int[] readData(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int n = scanner.nextInt();

                if (n <= 0) {
                    System.out.println("Input error. Size <= 0");
                } else {

                    int[] array = new int[n];

                    for (int i = 0; i < n; i++) {
                        if (scanner.hasNextInt()) {
                            array[i] = scanner.nextInt();
                        } else {
                            System.out.println("Could not parse a number. Please, try again");
                            scanner.next();
                            i--;
                        }
                    }
                    return array;
                }
            } else {
                System.out.println("Could not parse a number. Please, try again");
                scanner.next();
            }
        }
    }

    public static boolean hasMatchingFirstLastDigits(int number) {
        if (number < 10) {
            return true;
        }
        int lastDigit = number % 10;
        int firstDigit = number;
        while (firstDigit >= 10) {
            firstDigit = firstDigit / 10;
        }
        return firstDigit == lastDigit;
    }

    public static int[] findMatchingFirstLastDigit(int[] numbers) {
        int count = 0;
        for (int number : numbers) {
            if (hasMatchingFirstLastDigits(number)) {
                count++;
            }
        }

        if (count == 0) {
            System.out.println("There are no such elements");
            return new int[0];
        }

        int[] matching = new int[count];
        int index = 0;

        for (int number : numbers) {
            if (hasMatchingFirstLastDigits(number)) {
                matching[index] = number;
                index++;
            }
        }
        return matching;
    }

    public static void outputResult(int[] result) {
        for (int j : result) {
            System.out.printf("%d ", j);
        }
    }
}