package lab1.task_5;
import java.util.Scanner;

public class task5 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите трехзначное число: ");

        int number = scanner.nextInt();

        if (number < 100 || number > 999) {
            System.out.println("Число должно быть трехзначным.");
            return;
        }

        if (isTwiceEven(number)) {
            System.out.println(number + " - дважды четное число.");
        } else {
            System.out.println(number + " - не дважды четное число.");
        }

        scanner.close();
    }

    public static boolean isTwiceEven(int number) {
        int sum = 0;
        int product = 1;
        int digit;

        //Разделяем число на цифры и вычисляем сумму и произведение
        for (int i = 0; i < 3; i++) {
            digit = number % 10;
            sum += digit;
            product *= digit;
            number /= 10;
        }

        //Проверяем на четность
        return sum % 2 == 0 && product % 2 == 0;
    }
}