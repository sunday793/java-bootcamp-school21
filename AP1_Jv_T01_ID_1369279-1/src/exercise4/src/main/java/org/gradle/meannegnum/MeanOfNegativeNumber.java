package org.gradle.meannegnum;
import java.util.Scanner;

public class MeanOfNegativeNumber {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int[] n = readData(in);

        int result = calculateMeanOfNegativeNumber(n);

        outputResult(result);

        in.close();
    }

    public static int[] readData(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int n = scanner.nextInt();

                if (n <= 0) {
                    System.out.println("Input error. Size <= 0");
                    continue;
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

    public static int calculateMeanOfNegativeNumber(int[] numbers) {
        int negative = 0;
        int countNegative = 0;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < 0) {
                negative += numbers[i];
                countNegative += 1;
            }
        }
        if (negative == 0) {
            return 0;
        }

        return negative / countNegative;
    }

    public static void outputResult(long result) {
        if (result == 0) {
            System.out.println("There are no negative elements");
        } else {
            System.out.println(result);
        }
    }
}