package lab1.task_4;
import java.util.Scanner;

public class task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine()); // Количество дорог
        int maximum = 0; // Максимальная высота, найденная на данный момент
        int road = 1; // Номер дороги с максимальной высотой

        for (int element = 0; element < number; element++) {
            int tunnel = Integer.parseInt(scanner.nextLine()); // Количество туннелей на этой дороге
            int m = 100000000; // Минимальная высота на этой дороге, заданная как большое значение

            for (int element_2 = 0; element_2 < tunnel; element_2++) {
                int height = Integer.parseInt(scanner.nextLine()); // Высота текущего туннеля
                if (m > height) {
                    m = height; // Обновление минимальной высоты на этой дороге
                }
            }

            if (maximum < m) {
                maximum = m; // Обновление общей максимальной высоты
                road = element + 1; // Обновление номера дороги
            }
        }

        System.out.println(road + " " + maximum);
        scanner.close();
    }
}