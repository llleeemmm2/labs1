package lab1.task_2;
import java.util.Scanner;

public class task2 {
    public static void main(String[] args){
        Scanner c = new Scanner(System.in);
        int n = c.nextInt();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int number = c.nextInt();
            if (i % 2 == 0) {
                sum += number;
            }
            else {
                sum -= number;
            }
        }
        System.out.println(sum);
        c.close();
    }
}
