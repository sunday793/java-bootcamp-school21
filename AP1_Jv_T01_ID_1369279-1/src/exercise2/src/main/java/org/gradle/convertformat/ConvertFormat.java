package org.gradle.convertformat;
import java.util.Scanner;

public class ConvertFormat {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int totalSeconds = readData(in);

        int packedTime = calculateTime(totalSeconds);

        outputResult(packedTime);

        in.close();
    }

    public static int readData(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int seconds = scanner.nextInt();

                if (seconds < 0) {
                    System.out.println("Incorrect time.");
                    continue;
                }
                return seconds;
            } else {
                System.out.println("Could not parse a number. Please, try again");
                scanner.next();
            }
        }
    }

    public static int calculateTime(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        return (hours * 10000) + (minutes * 100) + seconds;
    }


    public static void outputResult(int packedTime) {
        int hours = packedTime / 10000;
        int minutes = (packedTime % 10000) / 100;
        int seconds = packedTime % 100;

        System.out.printf("%02d:%02d:%02d", hours, minutes, seconds);
    }
}