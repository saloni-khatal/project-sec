import java.util.Scanner;
import java.util.Vector;

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
    private Vector<Book> books;
    private Scanner scanner;

    public LibraryManagementSystem() {
        books = new Vector<>();
        scanner = new Scanner(System.in);

        books.add(new Book("To Kill a Mockingbird", "Harper Lee", 1960));
        books.add(new Book("1984", "George Orwell", 1949));
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925));
        books.add(new Book("Moby Dick", "Herman Melville", 1851));
        books.add(new Book("Pride and Prejudice", "Jane Austen", 1813));
        books.add(new Book("War and Peace", "Leo Tolstoy", 1869));
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", 1951));
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", 1937));
        books.add(new Book("One Hundred Years of Solitude", "Gabriel Garcia Marquez", 1967));
        books.add(new Book("Brave New World", "Aldous Huxley", 1932));
        books.add(new Book("Crime and Punishment", "Fyodor Dostoevsky", 1866));
        books.add(new Book("Ulysses", "James Joyce", 1922));
        books.add(new Book("Don Quixote", "Miguel de Cervantes", 1605));
    }

    public void run() {
        while (true) {
            System.out.println("Library Management System");
            System.out.println("1. Add book");
            System.out.println("2. Remove book");
            System.out.println("3. List books");
            System.out.println("4. Search book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    listBooks();
                    break;
                case 4:
                    searchBook();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
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

        books.add(new Book(title, author, year));
        System.out.println("Book added successfully!");
    }

    private void removeBook() {
        System.out.print("Enter book title to remove: ");
        String title = scanner.nextLine();

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equals(title)) {
                books.remove(i);
                System.out.println("Book removed successfully!");
                return;
            }
        }
        System.out.println("Book not found. Please try again.");
    }

    private void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : books) {
                System.out.println(book.getTitle() + " by " + book.getAuthor() + " (" + book.getYear() + ")");
            }
        }
    }

    private void searchBook() {
        System.out.print("Enter book title to search: ");
        String title = scanner.nextLine();

        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                System.out.println(book.getTitle() + " by " + book.getAuthor() + " (" + book.getYear() + ")");
                return;
            }
        }
        System.out.println("Book not found. Please try again.");
    }

    public static void main(String[] args) {
        LibraryManagementSystem system = new LibraryManagementSystem();
        system.run();
    }
}


