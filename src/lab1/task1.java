package lab1;
import java.util.Scanner;

public class task1 {
    public static void main(String[] args){
        Scanner c = new Scanner(System.in);
        long n = c.nextLong();
        int steps = 0;
        while (n != 1) {
            if (n % 2 == 0) {
                n /= 2;
            }
            else {
                n = 3 * n + 1;
            }
            steps++;
        }
        System.out.println(steps);
        c.close();
    }
}
