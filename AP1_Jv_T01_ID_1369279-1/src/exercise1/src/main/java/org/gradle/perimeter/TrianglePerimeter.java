package org.gradle.perimeter;
import java.util.Scanner;

public class TrianglePerimeter {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        float x1 = readData(in);
        float y1 = readData(in);
        float x2 = readData(in);
        float y2 = readData(in);
        float x3 = readData(in);
        float y3 = readData(in);

        float a = (float) Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
        float b = (float) Math.sqrt(Math.pow(x3-x2, 2) + Math.pow(y3-y2, 2));
        float c = (float) Math.sqrt(Math.pow(x1-x3, 2) + Math.pow(y1-y3, 2));

        if (a + b > c && b + c > a && a + c > b) {
            float perimeter = a + b + c;
            System.out.printf("Perimeter: %.3f", perimeter);
        } else {
            System.out.println("It's not a triangle");
        }

        in.close();
    }

    public static float readData(Scanner scanner) {
        while (true) {
            if (scanner.hasNextFloat()) {
                return scanner.nextFloat();
            } else {
                System.out.println("Could not parse a number. Please, try again");
                scanner.next();
            }
        }
    }
}
