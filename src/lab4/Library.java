package lab4;
import java.util.*;

public class Library {
    private List<Book> books = new ArrayList<>();
    private Set<String> authors = new HashSet<>();
    private Map<String, Integer> authorBookCount = new HashMap<>();

    public void addBook(Book book) {
        books.add(book);
        authors.add(book.getAuthor());
        authorBookCount.put(book.getAuthor(),
                authorBookCount.getOrDefault(book.getAuthor(), 0) + 1);
    }

    public void removeBook(Book book) {
        if (books.remove(book)) {
            int count = authorBookCount.get(book.getAuthor()) - 1;
            if (count == 0) {
                authors.remove(book.getAuthor());
                authorBookCount.remove(book.getAuthor());
            } else {
                authorBookCount.put(book.getAuthor(), count);
            }
        }
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> findBooksByYear(int year) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() == year) {
                result.add(book);
            }
        }
        return result;
    }

    public void printAllBooks() {
        books.forEach(System.out::println);
    }

    public void printUniqueAuthors() {
        authors.forEach(System.out::println);
    }

    public void printAuthorStatistics() {
        authorBookCount.forEach((author, count) ->
                System.out.println(author + ": " + count + " book(s)"));
    }
}
