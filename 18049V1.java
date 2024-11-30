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
    private Book[] books;
    private int bookCount;
    private Scanner scanner;

    public LibraryManagementSystem(int capacity) {
        books = new Book[capacity];  
        bookCount = 0;
        scanner = new Scanner(System.in);

        // Adding 13 books initially
        books[bookCount++] = new Book("To Kill a Mockingbird", "Harper Lee", 1960);
        books[bookCount++] = new Book("1984", "George Orwell", 1949);
        books[bookCount++] = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);
        books[bookCount++] = new Book("Moby Dick", "Herman Melville", 1851);
        books[bookCount++] = new Book("Pride and Prejudice", "Jane Austen", 1813);
        books[bookCount++] = new Book("War and Peace", "Leo Tolstoy", 1869);
        books[bookCount++] = new Book("The Catcher in the Rye", "J.D. Salinger", 1951);
        books[bookCount++] = new Book("The Hobbit", "J.R.R. Tolkien", 1937);
        books[bookCount++] = new Book("One Hundred Years of Solitude", "Gabriel Garcia Marquez", 1967);
        books[bookCount++] = new Book("Brave New World", "Aldous Huxley", 1932);
        books[bookCount++] = new Book("Crime and Punishment", "Fyodor Dostoevsky", 1866);
        books[bookCount++] = new Book("Ulysses", "James Joyce", 1922);
        books[bookCount++] = new Book("Don Quixote", "Miguel de Cervantes", 1605);
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
        if (bookCount >= books.length) {
            System.out.println("Library is full. Cannot add more books.");
            return;
        }

        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book year: ");
        int year = Integer.parseInt(scanner.nextLine());

        books[bookCount++] = new Book(title, author, year);  
        System.out.println("Book added successfully!");
    }

    private void removeBook() {
        System.out.print("Enter book title to remove: ");
        String title = scanner.nextLine();

        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().equals(title)) {
                for (int j = i; j < bookCount - 1; j++) {
                    books[j] = books[j + 1];
                }
                books[--bookCount] = null;  
                System.out.println("Book removed successfully!");
                return;
            }
        }
        System.out.println("Book not found. Please try again.");
    }

    private void listBooks() {
        if (bookCount == 0) {
            System.out.println("No books available.");
        } else {
            for (int i = 0; i < bookCount; i++) {
                System.out.println(books[i].getTitle() + " by " + books[i].getAuthor() + " (" + books[i].getYear() + ")");
            }
        }
    }

    private void searchBook() {
        System.out.print("Enter book title to search: ");
        String title = scanner.nextLine();

        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().equals(title)) {
                System.out.println(books[i].getTitle() + " by " + books[i].getAuthor() + " (" + books[i].getYear() + ")");
                return;
            }
        }
        System.out.println("Book not found. Please try again.");
    }

    public static void main(String[] args) {
        // Set capacity to 20 to accommodate future additions
        LibraryManagementSystem system = new LibraryManagementSystem(20); 
        system.run();
  }
}