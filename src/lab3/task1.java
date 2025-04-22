package lab3;

import java.util.*;
import java.time.*;
import java.time.format.*;

class CinemaSystem {
    // Структуры данных для хранения информации
    static Map<String, String> users = new HashMap<>(); // Логин -> Пароль
    static Map<String, String> admins = new HashMap<>(); // Логин -> Пароль
    static List<Cinema> cinemas = new ArrayList<>();
    static List<Movie> movies = new ArrayList<>();

    public static void main(String[] args) {
        initializeTestData();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Билетная система кинотеатров ===");
            System.out.println("1. Вход как администратор");
            System.out.println("2. Вход как пользователь");
            System.out.println("3. Выход");
            System.out.print("Выберите вариант: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    adminLogin(scanner);
                    break;
                case 2:
                    userLogin(scanner);
                    break;
                case 3:
                    System.out.println("До свидания!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    static void initializeTestData() {
        // Тестовые пользователи
        users.put("user1", "pass1");
        users.put("user2", "pass2");

        // Тестовые администраторы
        admins.put("admin", "admin");

        // Тестовые фильмы
        movies.add(new Movie("Форсаж 10", Duration.ofMinutes(141)));
        movies.add(new Movie("Стражи Галактики 3", Duration.ofMinutes(150)));
        movies.add(new Movie("Человек-паук: Паутина вселенных", Duration.ofMinutes(116)));

        // Тестовые кинотеатры
        Cinema cinema1 = new Cinema("Киномакс", "ул. Кирова, 15");
        Cinema cinema2 = new Cinema("Киноплекс", "пр. Ленина, 42");

        // Залы для первого кинотеатра
        Hall hall1 = new Hall("Зал 1", 5, 10);
        Hall hall2 = new Hall("Зал 2", 8, 12);

        // Залы для второго кинотеатра
        Hall hall3 = new Hall("Зал 1", 6, 8);
        Hall hall4 = new Hall("Зал VIP", 4, 6);

        cinema1.addHall(hall1);
        cinema1.addHall(hall2);
        cinema2.addHall(hall3);
        cinema2.addHall(hall4);

        cinemas.add(cinema1);
        cinemas.add(cinema2);

        // Тестовые сеансы
        LocalDateTime now = LocalDateTime.now();
        hall1.addSession(new Session(movies.get(0), now.plusHours(1)));
        hall1.addSession(new Session(movies.get(1), now.plusHours(4)));
        hall2.addSession(new Session(movies.get(2), now.plusHours(2)));
        hall3.addSession(new Session(movies.get(0), now.plusHours(3)));
        hall4.addSession(new Session(movies.get(1), now.plusHours(1).plusMinutes(30)));
    }

    static void adminLogin(Scanner scanner) {
        System.out.print("\nВведите логин администратора: ");
        String login = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        if (admins.containsKey(login) && admins.get(login).equals(password)) {
            adminMenu(scanner);
        } else {
            System.out.println("Неверный логин или пароль!");
        }
    }

    static void adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n=== Меню администратора ===");
            System.out.println("1. Добавить кинотеатр");
            System.out.println("2. Добавить зал в кинотеатр");
            System.out.println("3. Добавить фильм");
            System.out.println("4. Создать сеанс");
            System.out.println("5. Просмотреть все кинотеатры");
            System.out.println("6. Выход");
            System.out.print("Выберите вариант: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    addCinema(scanner);
                    break;
                case 2:
                    addHall(scanner);
                    break;
                case 3:
                    addMovie(scanner);
                    break;
                case 4:
                    createSession(scanner);
                    break;
                case 5:
                    viewAllCinemas();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    static void addCinema(Scanner scanner) {
        System.out.print("\nВведите название кинотеатра: ");
        String name = scanner.nextLine();
        System.out.print("Введите адрес: ");
        String address = scanner.nextLine();

        cinemas.add(new Cinema(name, address));
        System.out.println("Кинотеатр успешно добавлен!");
    }

    static void addHall(Scanner scanner) {
        if (cinemas.isEmpty()) {
            System.out.println("Сначала добавьте кинотеатр!");
            return;
        }

        System.out.println("\nСписок кинотеатров:");
        for (int i = 0; i < cinemas.size(); i++) {
            System.out.println((i+1) + ". " + cinemas.get(i).getName());
        }

        System.out.print("Выберите кинотеатр: ");
        int cinemaIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Очистка буфера

        if (cinemaIndex < 0 || cinemaIndex >= cinemas.size()) {
            System.out.println("Неверный выбор!");
            return;
        }

        System.out.print("Введите название зала: ");
        String name = scanner.nextLine();
        System.out.print("Введите количество рядов: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество мест в ряду: ");
        int seatsPerRow = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        cinemas.get(cinemaIndex).addHall(new Hall(name, rows, seatsPerRow));
        System.out.println("Зал успешно добавлен!");
    }

    static void addMovie(Scanner scanner) {
        System.out.print("\nВведите название фильма: ");
        String title = scanner.nextLine();
        System.out.print("Введите продолжительность в минутах: ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        movies.add(new Movie(title, Duration.ofMinutes(duration)));
        System.out.println("Фильм успешно добавлен!");
    }

    static void createSession(Scanner scanner) {
        if (cinemas.isEmpty()) {
            System.out.println("Нет кинотеатров!");
            return;
        }

        if (movies.isEmpty()) {
            System.out.println("Нет фильмов!");
            return;
        }

        System.out.println("\nСписок кинотеатров:");
        for (int i = 0; i < cinemas.size(); i++) {
            System.out.println((i+1) + ". " + cinemas.get(i).getName());
        }

        System.out.print("Выберите кинотеатр: ");
        int cinemaIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Очистка буфера

        if (cinemaIndex < 0 || cinemaIndex >= cinemas.size()) {
            System.out.println("Неверный выбор!");
            return;
        }

        Cinema cinema = cinemas.get(cinemaIndex);

        if (cinema.getHalls().isEmpty()) {
            System.out.println("В этом кинотеатре нет залов!");
            return;
        }

        System.out.println("\nСписок залов:");
        List<Hall> halls = cinema.getHalls();
        for (int i = 0; i < halls.size(); i++) {
            System.out.println((i+1) + ". " + halls.get(i).getName());
        }

        System.out.print("Выберите зал: ");
        int hallIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Очистка буфера

        if (hallIndex < 0 || hallIndex >= halls.size()) {
            System.out.println("Неверный выбор!");
            return;
        }

        System.out.println("\nСписок фильмов:");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i+1) + ". " + movies.get(i).getTitle());
        }

        System.out.print("Выберите фильм: ");
        int movieIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Очистка буфера

        if (movieIndex < 0 || movieIndex >= movies.size()) {
            System.out.println("Неверный выбор!");
            return;
        }

        System.out.print("Введите дату и время сеанса (гггг-мм-дд чч:мм): ");
        String dateTimeStr = scanner.nextLine();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);

            halls.get(hallIndex).addSession(new Session(movies.get(movieIndex), dateTime));
            System.out.println("Сеанс успешно создан!");
        } catch (Exception e) {
            System.out.println("Неверный формат даты и времени!");
        }
    }

    static void viewAllCinemas() {
        if (cinemas.isEmpty()) {
            System.out.println("Нет кинотеатров!");
            return;
        }

        System.out.println("\nСписок кинотеатров:");
        for (Cinema cinema : cinemas) {
            System.out.println("\nКинотеатр: " + cinema.getName());
            System.out.println("Адрес: " + cinema.getAddress());

            if (cinema.getHalls().isEmpty()) {
                System.out.println("  Нет залов");
                continue;
            }

            System.out.println("Залы:");
            for (Hall hall : cinema.getHalls()) {
                System.out.println("  " + hall.getName() + " (Ряды: " + hall.getRows() +
                        ", Мест в ряду: " + hall.getSeatsPerRow() + ")");

                if (hall.getSessions().isEmpty()) {
                    System.out.println("    Нет сеансов");
                    continue;
                }

                System.out.println("    Сеансы:");
                for (Session session : hall.getSessions()) {
                    System.out.println("      Фильм: " + session.getMovie().getTitle());
                    System.out.println("      Время: " + session.getDateTime().format(
                            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
                    System.out.println("      Свободных мест: " + session.getAvailableSeatsCount());
                    System.out.println();
                }
            }
        }
    }

    static void userLogin(Scanner scanner) {
        System.out.print("\nВведите логин пользователя: ");
        String login = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        if (users.containsKey(login) && users.get(login).equals(password)) {
            userMenu(scanner);
        } else {
            System.out.println("Неверный логин или пароль!");
        }
    }

    static void userMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n=== Меню пользователя ===");
            System.out.println("1. Поиск ближайшего сеанса");
            System.out.println("2. Купить билет");
            System.out.println("3. Просмотреть план зала");
            System.out.println("4. Выход");
            System.out.print("Выберите вариант: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    findNearestSession(scanner);
                    break;
                case 2:
                    buyTicket(scanner);
                    break;
                case 3:
                    viewHallPlan(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    static void findNearestSession(Scanner scanner) {
        if (movies.isEmpty()) {
            System.out.println("Нет фильмов!");
            return;
        }

        System.out.println("\nСписок фильмов:");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i+1) + ". " + movies.get(i).getTitle());
        }

        System.out.print("Выберите фильм: ");
        int movieIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Очистка буфера

        if (movieIndex < 0 || movieIndex >= movies.size()) {
            System.out.println("Неверный выбор!");
            return;
        }

        Movie selectedMovie = movies.get(movieIndex);
        Session nearestSession = null;
        Cinema nearestCinema = null;
        Hall nearestHall = null;
        LocalDateTime now = LocalDateTime.now();

        for (Cinema cinema : cinemas) {
            for (Hall hall : cinema.getHalls()) {
                for (Session session : hall.getSessions()) {
                    if (session.getMovie().equals(selectedMovie) &&
                            session.getDateTime().isAfter(now) &&
                            session.getAvailableSeatsCount() > 0) {

                        if (nearestSession == null ||
                                session.getDateTime().isBefore(nearestSession.getDateTime())) {
                            nearestSession = session;
                            nearestCinema = cinema;
                            nearestHall = hall;
                        }
                    }
                }
            }
        }

        if (nearestSession == null) {
            System.out.println("Нет доступных сеансов для выбранного фильма!");
        } else {
            System.out.println("\nБлижайший сеанс:");
            System.out.println("Кинотеатр: " + nearestCinema.getName());
            System.out.println("Адрес: " + nearestCinema.getAddress());
            System.out.println("Зал: " + nearestHall.getName());
            System.out.println("Время: " + nearestSession.getDateTime().format(
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
            System.out.println("Свободных мест: " + nearestSession.getAvailableSeatsCount());
        }
    }

    static void buyTicket(Scanner scanner) {
        if (cinemas.isEmpty()) {
            System.out.println("Нет кинотеатров!");
            return;
        }

        System.out.println("\nСписок кинотеатров:");
        for (int i = 0; i < cinemas.size(); i++) {
            System.out.println((i+1) + ". " + cinemas.get(i).getName());
        }

        System.out.print("Выберите кинотеатр: ");
        int cinemaIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Очистка буфера

        if (cinemaIndex < 0 || cinemaIndex >= cinemas.size()) {
            System.out.println("Неверный выбор!");
            return;
        }

        Cinema cinema = cinemas.get(cinemaIndex);

        if (cinema.getHalls().isEmpty()) {
            System.out.println("В этом кинотеатре нет залов!");
            return;
        }

        System.out.println("\nСписок залов:");
        List<Hall> halls = cinema.getHalls();
        for (int i = 0; i < halls.size(); i++) {
            System.out.println((i+1) + ". " + halls.get(i).getName());
        }

        System.out.print("Выберите зал: ");
        int hallIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Очистка буфера

        if (hallIndex < 0 || hallIndex >= halls.size()) {
            System.out.println("Неверный выбор!");
            return;
        }

        Hall hall = halls.get(hallIndex);

        if (hall.getSessions().isEmpty()) {
            System.out.println("В этом зале нет сеансов!");
            return;
        }

        System.out.println("\nСписок сеансов:");
        List<Session> sessions = hall.getSessions();
        for (int i = 0; i < sessions.size(); i++) {
            Session session = sessions.get(i);
            System.out.println((i+1) + ". " + session.getMovie().getTitle() +
                    " - " + session.getDateTime().format(
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) +
                    " (Свободных мест: " + session.getAvailableSeatsCount() + ")");
        }

        System.out.print("Выберите сеанс: ");
        int sessionIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Очистка буфера

        if (sessionIndex < 0 || sessionIndex >= sessions.size()) {
            System.out.println("Неверный выбор!");
            return;
        }

        Session session = sessions.get(sessionIndex);

        if (session.getAvailableSeatsCount() == 0) {
            System.out.println("На этот сеанс нет свободных мест!");
            return;
        }

        // Показываем план зала
        System.out.println("\nПлан зала (X - занято, O - свободно):");
        boolean[][] seats = session.getSeats();
        System.out.print("   ");
        for (int col = 0; col < seats[0].length; col++) {
            System.out.printf("%2d ", col + 1);
        }
        System.out.println();

        for (int row = 0; row < seats.length; row++) {
            System.out.printf("%2d ", row + 1);
            for (int col = 0; col < seats[row].length; col++) {
                System.out.print(seats[row][col] ? " X " : " O ");
            }
            System.out.println();
        }

        System.out.print("\nВведите номер ряда: ");
        int row = scanner.nextInt() - 1;
        System.out.print("Введите номер места: ");
        int seat = scanner.nextInt() - 1;
        scanner.nextLine(); // Очистка буфера

        if (row < 0 || row >= seats.length || seat < 0 || seat >= seats[0].length) {
            System.out.println("Неверный выбор места!");
            return;
        }

        if (session.bookSeat(row, seat)) {
            System.out.println("Билет успешно куплен!");
        } else {
            System.out.println("Это место уже занято!");
        }
    }

    static void viewHallPlan(Scanner scanner) {
        if (cinemas.isEmpty()) {
            System.out.println("Нет кинотеатров!");
            return;
        }

        System.out.println("\nСписок кинотеатров:");
        for (int i = 0; i < cinemas.size(); i++) {
            System.out.println((i+1) + ". " + cinemas.get(i).getName());
        }

        System.out.print("Выберите кинотеатр: ");
        int cinemaIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Очистка буфера

        if (cinemaIndex < 0 || cinemaIndex >= cinemas.size()) {
            System.out.println("Неверный выбор!");
            return;
        }

        Cinema cinema = cinemas.get(cinemaIndex);

        if (cinema.getHalls().isEmpty()) {
            System.out.println("В этом кинотеатре нет залов!");
            return;
        }

        System.out.println("\nСписок залов:");
        List<Hall> halls = cinema.getHalls();
        for (int i = 0; i < halls.size(); i++) {
            System.out.println((i+1) + ". " + halls.get(i).getName());
        }

        System.out.print("Выберите зал: ");
        int hallIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Очистка буфера

        if (hallIndex < 0 || hallIndex >= halls.size()) {
            System.out.println("Неверный выбор!");
            return;
        }

        Hall hall = halls.get(hallIndex);

        if (hall.getSessions().isEmpty()) {
            System.out.println("В этом зале нет сеансов!");
            return;
        }

        System.out.println("\nСписок сеансов:");
        List<Session> sessions = hall.getSessions();
        for (int i = 0; i < sessions.size(); i++) {
            Session session = sessions.get(i);
            System.out.println((i+1) + ". " + session.getMovie().getTitle() +
                    " - " + session.getDateTime().format(
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) +
                    " (Свободных мест: " + session.getAvailableSeatsCount() + ")");
        }

        System.out.print("Выберите сеанс: ");
        int sessionIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Очистка буфера

        if (sessionIndex < 0 || sessionIndex >= sessions.size()) {
            System.out.println("Неверный выбор!");
            return;
        }

        Session session = sessions.get(sessionIndex);

        System.out.println("\nПлан зала (X - занято, O - свободно):");
        boolean[][] seats = session.getSeats();
        System.out.print("   ");
        for (int col = 0; col < seats[0].length; col++) {
            System.out.printf("%2d ", col + 1);
        }
        System.out.println();

        for (int row = 0; row < seats.length; row++) {
            System.out.printf("%2d ", row + 1);
            for (int col = 0; col < seats[row].length; col++) {
                System.out.print(seats[row][col] ? " X " : " O ");
            }
            System.out.println();
        }
    }
}

// Классы модели
class Cinema {
    private String name;
    private String address;
    private List<Hall> halls = new ArrayList<>();

    public Cinema(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public List<Hall> getHalls() { return halls; }

    public void addHall(Hall hall) {
        halls.add(hall);
    }
}

class Hall {
    private String name;
    private int rows;
    private int seatsPerRow;
    private List<Session> sessions = new ArrayList<>();

    public Hall(String name, int rows, int seatsPerRow) {
        this.name = name;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
    }

    public String getName() { return name; }
    public int getRows() { return rows; }
    public int getSeatsPerRow() { return seatsPerRow; }
    public List<Session> getSessions() { return sessions; }

    public void addSession(Session session) {
        sessions.add(session);
    }
}

class Movie {
    private String title;
    private Duration duration;

    public Movie(String title, Duration duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() { return title; }
    public Duration getDuration() { return duration; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movie movie = (Movie) obj;
        return title.equals(movie.title) && duration.equals(movie.duration);
    }
}

class Session {
    private Movie movie;
    private LocalDateTime dateTime;
    private boolean[][] seats;

    public Session(Movie movie, LocalDateTime dateTime) {
        this.movie = movie;
        this.dateTime = dateTime;
        // По умолчанию все места свободны
        this.seats = new boolean[10][10]; // Стандартный размер зала
    }

    public Movie getMovie() { return movie; }
    public LocalDateTime getDateTime() { return dateTime; }
    public boolean[][] getSeats() { return seats; }

    public int getAvailableSeatsCount() {
        int count = 0;
        for (boolean[] row : seats) {
            for (boolean seat : row) {
                if (!seat) count++;
            }
        }
        return count;
    }

    public boolean bookSeat(int row, int seat) {
        if (row < 0 || row >= seats.length || seat < 0 || seat >= seats[0].length) {
            return false;
        }

        if (seats[row][seat]) {
            return false; // Место уже занято
        }

        seats[row][seat] = true;
        return true;
    }
}
