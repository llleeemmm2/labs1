package lab2.task_2;
import java.util.Arrays;
import java.util.Scanner;

public class task2 {
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }

        while (i < arr1.length) {
            result[k++] = arr1[i++];
        }

        while (j < arr2.length) {
            result[k++] = arr2[j++];
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер первого массива: ");
        int size1 = scanner.nextInt();
        int[] arr1 = new int[size1];
        System.out.println("Введите элементы первого массива (отсортированные):");
        for (int i = 0; i < size1; i++) {
            arr1[i] = scanner.nextInt();
        }

        System.out.print("Введите размер второго массива: ");
        int size2 = scanner.nextInt();
        int[] arr2 = new int[size2];
        System.out.println("Введите элементы второго массива (отсортированные):");
        for (int i = 0; i < size2; i++) {
            arr2[i] = scanner.nextInt();
        }

        int[] merged = mergeSortedArrays(arr1, arr2);
        System.out.println("Объединенный массив: " + Arrays.toString(merged));

        scanner.close();
    }
}
