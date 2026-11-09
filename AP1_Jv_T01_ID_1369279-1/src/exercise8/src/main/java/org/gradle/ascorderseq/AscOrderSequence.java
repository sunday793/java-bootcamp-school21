package org.gradle.ascorderseq;
import java.util.Scanner;

public class AscOrderSequence {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        checkingSequence(in);

        in.close();
    }

    public static void checkingSequence(Scanner scanner) {
        String line = scanner.nextLine();
        Scanner lineScanner = new Scanner(line);

        int count = 0;
        int previous = 0;
        boolean isOrdered = true;
        int violationIndex = -1;

        while (lineScanner.hasNext()) {
            if (lineScanner.hasNextInt()) {
                int num = lineScanner.nextInt();
                count++;

                if (count == 1) {
                    previous = num;
                } else if (num < previous) {
                    isOrdered = false;
                    violationIndex = count-1;
                    break;
                } else {
                    previous = num;
                }
            }
            else {
                break;
            }
        }

        if (count == 0) {
            System.out.println("Input error");
        } else if (isOrdered) {
            System.out.println("The sequence is ordered in ascending order");
        } else {
            System.out.println("The sequence is not ordered from the ordinal number of the number " + violationIndex);
        }

        lineScanner.close();
    }
}