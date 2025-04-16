package lab2.task_1;

import java.util.HashMap;
import java.util.Scanner;

public class task1 {
    public static String longestUniqueSubstring(String s) {
        if (s == null || s.isEmpty()) return "";

        HashMap<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int start = 0;
        int resultStart = 0;

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (map.containsKey(currentChar) && map.get(currentChar) >= start) {
                start = map.get(currentChar) + 1;
            }
            map.put(currentChar, i);
            if (i - start + 1 > maxLength) {
                maxLength = i - start + 1;
                resultStart = start;
            }
        }

        return s.substring(resultStart, resultStart + maxLength);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку для поиска подстроки без повторений: ");
        String input = scanner.nextLine();

        String result = longestUniqueSubstring(input);
        System.out.println("Наибольшая подстрока без повторений: " + result);

        scanner.close();
    }
}