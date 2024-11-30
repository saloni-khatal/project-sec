import java.sql.*;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }
}

public class LibraryManagementSystem {
    private Connection connection;
    private Scanner scanner;

    public LibraryManagementSystem() {
        scanner = new Scanner(System.in);
        connectToDatabase();
    }

    private void connectToDatabase() {
        try {
            // Connect to MySQL database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/library", "root", "");
            System.out.println("Connected to the database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void run() {
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add book");
            System.out.println("2. Remove book");
            System.out.println("3. List books");
            System.out.println("4. Search book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addBook();
                case 2 -> removeBook();
                case 3 -> listBooks();
                case 4 -> searchBook();
                case 5 -> {
                    closeConnection();
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book year: ");
        int year = Integer.parseInt(scanner.nextLine());

        String query = "INSERT INTO books (title, author, year) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setInt(3, year);
            stmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removeBook() {
        System.out.print("Enter book title to remove: ");
        String title = scanner.nextLine();

        String query = "DELETE FROM books WHERE title = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, title);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book removed successfully!");
            } else {
                System.out.println("Book not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void listBooks() {
        String query = "SELECT * FROM books";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            boolean hasBooks = false;
            while (rs.next()) {
                hasBooks = true;
                System.out.println(rs.getString("title") + " by " +
                                   rs.getString("author") + " (" +
                                   rs.getInt("year") + ")");
            }
            if (!hasBooks) {
                System.out.println("No books available.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void searchBook() {
        System.out.print("Enter book title to search: ");
        String title = scanner.nextLine();

        String query = "SELECT * FROM books WHERE title = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, title);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println(rs.getString("title") + " by " +
                                       rs.getString("author") + " (" +
                                       rs.getInt("year") + ")");
                } else {
                    System.out.println("Book not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LibraryManagementSystem system = new LibraryManagementSystem();
        system.run();
    }
}