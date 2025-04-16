package lab2.task_6;

import java.util.Scanner;

public class task6 {
    public static int sum2DArray(int[][] matrix) {
        if (matrix == null) return 0;

        int sum = 0;
        for (int[] row : matrix) {
            for (int num : row) {
                sum += num;
            }
        }

        return sum;
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

        int sum = sum2DArray(matrix);
        System.out.println("Сумма всех элементов: " + sum);

        scanner.close();
    }
}