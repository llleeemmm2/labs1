package lab2.task_5;

import java.util.HashMap;
import java.util.Scanner;

public class task5 {
    public static int[] findPairWithSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) return null;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{complement, nums[i]};
            }
            map.put(nums[i], i);
        }

        return null;
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

        System.out.print("Введите target сумму: ");
        int target = scanner.nextInt();

        int[] pair = findPairWithSum(nums, target);
        if (pair != null) {
            System.out.println("Пара элементов: " + pair[0] + " и " + pair[1]);
        } else {
            System.out.println("Пара не найдена");
        }

        scanner.close();
    }
}
