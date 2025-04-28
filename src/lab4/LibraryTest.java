package lab4;

public class LibraryTest {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("Война и мир", "Лев Толстой", 1869);
        Book book2 = new Book("Анна Каренина", "Лев Толстой", 1877);
        Book book3 = new Book("Преступление и наказание", "Федор Достоевский", 1866);
        Book book4 = new Book("Идиот", "Федор Достоевский", 1869);
        Book book5 = new Book("Мастер и Маргарита", "Михаил Булгаков", 1967);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);

        System.out.println("Все книги:");
        library.printAllBooks();

        System.out.println("\nУникальные авторы:");
        library.printUniqueAuthors();

        System.out.println("\nСтатистика:");
        library.printAuthorStatistics();

        System.out.println("\nКниги Толстого:");
        library.findBooksByAuthor("Лев Толстой").forEach(System.out::println);

        System.out.println("\nКниги 1869 года:");
        library.findBooksByYear(1869).forEach(System.out::println);

        library.removeBook(book1);
        System.out.println("\nПосле удаления:");
        library.printAllBooks();
    }
}
