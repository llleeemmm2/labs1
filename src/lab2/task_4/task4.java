package lab2.task_4;

import java.util.Scanner;

public class task4 {
    public static int[][] rotate90Clockwise(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return new int[0][0];

        int n = matrix.length;
        int[][] rotated = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][n - 1 - i] = matrix[i][j];
            }
        }

        return rotated;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер квадратной матрицы: ");
        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];

        System.out.println("Введите элементы матрицы построчно:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        int[][] rotated = rotate90Clockwise(matrix);
        System.out.println("Повернутая матрица:");
        for (int[] row : rotated) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}
