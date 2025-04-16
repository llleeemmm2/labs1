package lab2.task_3;

import java.util.Scanner;

public class task3 {
    public static int maxSubarraySum(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();
        int[] nums = new int[size];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < size; i++) {
            nums[i] = scanner.nextInt();
        }

        int maxSum = maxSubarraySum(nums);
        System.out.println("Максимальная сумма подмассива: " + maxSum);

        scanner.close();
    }
}