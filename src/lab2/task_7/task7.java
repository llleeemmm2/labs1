package lab2.task_7;

import java.util.Scanner;

public class task7 {
    public static int[] maxInEachRow(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return new int[0];

        int[] result = new int[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] == null || matrix[i].length == 0) {
                result[i] = Integer.MIN_VALUE;
                continue;
            }

            int max = matrix[i][0];
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
            result[i] = max;
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество строк: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество столбцов: ");
        int cols = scanner.nextInt();

        int[][] matrix = new int[rows][cols];
        System.out.println("Введите элементы матрицы построчно:");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        int[] maxValues = maxInEachRow(matrix);
        System.out.println("Максимальные элементы в каждой строке:");
        for (int max : maxValues) {
            System.out.print(max + " ");
        }

        scanner.close();
    }
}
