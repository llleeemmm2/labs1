package lab1.task_3;

import java.util.Scanner;

public class task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int current_x = 0; // Текущее положение по x
        int current_y = 0; // Текущее положение по y
        int steps_taken = 0; // Счётчик инструкций
        boolean treasureFound = true; // Флаг, показывающий, достигнута ли цель

        int target_x = Integer.parseInt(scanner.nextLine()); // Координата сокровища по x
        int target_y = Integer.parseInt(scanner.nextLine()); // Координата сокровища по y

        while (true) {
            if (target_x == current_x && target_y == current_y) {
                treasureFound = false;
            }

            String direction = scanner.nextLine(); // Временная переменная для хранения строки с направлением движения

            if (direction.equals("стоп")) {
                break;
            }

            int steps = Integer.parseInt(scanner.nextLine()); // Количество шагов в текущей инструкции

            if (direction.equals("север")) {
                current_y += steps;
                if (treasureFound) {
                    steps_taken += 1;
                }
            } else if (direction.equals("запад")) {
                current_x -= steps;
                if (treasureFound) {
                    steps_taken += 1;
                }
            } else if (direction.equals("юг")) {
                current_y -= steps;
                if (treasureFound) {
                    steps_taken += 1;
                }
            } else if (direction.equals("восток")) {
                current_x += steps;
                if (treasureFound) {
                    steps_taken += 1;
                }
            }
        }
        System.out.println(steps_taken);
        scanner.close();
    }
}