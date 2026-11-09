package org.gradle.selectionsort;
import java.util.Scanner;

public class SelectionSort {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        float[] n = readData(in);

        float[] result = doingSelectionSort(n);

        outputResult(result);

        in.close();
    }

    public static float[] readData(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int n = scanner.nextInt();

                if (n <= 0) {
                    System.out.println("Input error. Size <= 0");
                } else {

                    float[] array = new float[n];

                    for (int i = 0; i < n; i++) {
                        if (scanner.hasNextFloat()) {
                            array[i] = scanner.nextFloat();
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

    public static float[] doingSelectionSort(float[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            int minIndex = i;
            for (int j = i+1; j < numbers.length; j++) {
                if (numbers[j] < numbers[minIndex]) {
                    minIndex = j;
                }
            }
            float temp = numbers[i];
            numbers[i] = numbers[minIndex];
            numbers[minIndex] = temp;
        }
        return numbers;
    }

    public static void outputResult(float[] result) {
        for (int i = 0; i < result.length; i++) {
            System.out.printf("%.1f ",result[i]);
        }
    }
}