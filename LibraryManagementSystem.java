import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class User {
    private String name;
    private int id;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}

public class LibraryManagementSystem {
    private Map<Integer, Book> books;
    private Map<Integer, User> users;

    public LibraryManagementSystem() {
        books = new HashMap<>();
        users = new HashMap<>();
    }

    public void addBook(int id, String title, String author) {
        books.put(id, new Book(title, author));
    }

    public void addUser(int id, String name) {
        users.put(id, new User(name, id));
    }

    public void borrowBook(int bookId, int userId) {
        Book book = books.get(bookId);
        User user = users.get(userId);

        if (book != null && user != null && book.isAvailable()) {
            book.setAvailable(false);
            System.out.println(user.getName() + " borrowed " + book.getTitle());
        } else {
            System.out.println("Book not available or user not found.");
        }
    }

    public void returnBook(int bookId, int userId) {
        Book book = books.get(bookId);
        User user = users.get(userId);

        if (book != null && user != null && !book.isAvailable()) {
            book.setAvailable(true);
            System.out.println(user.getName() + " returned " + book.getTitle());
        } else {
            System.out.println("Book not borrowed or user not found.");
        }
    }

    public List<Book> searchBooks(String query) {
        List<Book> results = new ArrayList<>();

        for (Book book : books.values()) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase()) || book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                results.add(book);
            }
        }

        return results;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryManagementSystem library = new LibraryManagementSystem();
        
        System.out.println("Welcome to the Library Management System.");
        
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add a book");
            System.out.println("2. Add a user");
            System.out.println("3. Borrow a book");
            System.out.println("4. Return a book");
            System.out.println("5. Search for books");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("Enter book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter book title: ");
                    String bookTitle = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String bookAuthor = scanner.nextLine();
                    library.addBook(bookId, bookTitle, bookAuthor);
                    break;
                case 2:
                    System.out.print("Enter user ID: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter user name: ");
                    String userName = scanner.nextLine();
                    library.addUser(userId, userName);
                    break;
                case 3:
                    System.out.print("Enter book ID to borrow: ");
                    int borrowBookId = scanner.nextInt();
                    System.out.print("Enter user ID borrowing the book: ");
                    int borrowUserId = scanner.nextInt();
                    library.borrowBook(borrowBookId, borrowUserId);
                    break;
                case 4:
                    System.out.print("Enter book ID to return: ");
                    int returnBookId = scanner.nextInt();
                    System.out.print("Enter user ID returning the book: ");
                    int returnUserId = scanner.nextInt();
                    library.returnBook(returnBookId, returnUserId);
                    break;
                case 5:
                    System.out.print("Enter search query: ");
                    String searchQuery = scanner.nextLine();
                    List<Book> searchResults = library.searchBooks(searchQuery);
                    System.out.println("Search Results:");
                    for (Book book : searchResults) {
                        System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor());
                    }
                    break;
                case 6:
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 6.");
            }
        }
    }
}
