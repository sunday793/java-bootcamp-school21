package org.gradle.fibonaccinumber;
import java.util.Scanner;

public class FibonacciNumber {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int n = readData(in);

        long result = calculateFibonacci(n);

        if (result >= 0) {
            outputResult(result);
        }

        in.close();
    }

    public static int readData(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int n = scanner.nextInt();

                if (n < 0) {
                    System.out.println("Could not parse a number. Please, try again");
                    continue;
                }
                return n;
            } else {
                System.out.println("Could not parse a number. Please, try again");
                scanner.next();
            }
        }
    }

    public static long calculateFibonacci(int n) {
        if (n <= 0) return 0;
        if (n == 1 || n == 2) return 1;

        if (n > 92) {
            System.out.println("Too large n");
            return -1;
        }

        long a = calculateFibonacci(n-1);
        long b = calculateFibonacci(n-2);

        return a + b;
    }

    public static void outputResult(long result) {
        System.out.println(result);
    }
}