package org.gradle.stringfiltering;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class StringFiltering {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<String> words = readStrings(scanner);

        String subString = scanner.nextLine();

        List<String> resultList = filterString(words, subString);

        outputResult(resultList);

        scanner.close();
    }

    public static List<String> readStrings(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine());
        List<String> words = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            words.add(scanner.nextLine());
        }
        return words;
    }

    public static List<String> filterString(List<String> words, String subString) {
        List<String> filteredList = new ArrayList<String>();

        for (String word: words) {
            boolean contains = false;

            for (int i = 0; i <= word.length() - subString.length(); i++) {
                boolean match = true;

                for (int j = 0; j < subString.length(); j++) {
                    if (word.charAt(i + j) != subString.charAt(j)) {
                        match = false;
                        break;
                    }

                }
                if (match) {
                    contains = true;
                    break;
                }
            }
            if (contains) {
                filteredList.add(word);
            }
        }
        return filteredList;
    }

    public static void outputResult(List<String> words) {
        if (words.isEmpty()) {
            System.out.println(" ");
        } else {
            for (int i = 0; i < words.size(); i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(words.get(i));
            }
            System.out.println();
        }
    }
}