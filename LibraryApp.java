import java.util.Scanner;

class Book {
    String title;
    String author;
    boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public void display() {
        System.out.println("Title: " + title + ", Author: " + author +
                ", Status: " + (isAvailable ? "Available" : "Borrowed"));
    }
}

class Library {
    Book[] books;
    int count;

    public Library(int size) {
        books = new Book[size];
        count = 0;
    }

    public void addBook(Book book) {
        if (count < books.length) {
            books[count++] = book;
            System.out.println("Book added successfully!");
        } else {
            System.out.println("Library is full! Cannot add more books.");
        }
    }

    // Method Overloading - Search by Title
    public Book searchBook(String title) {
        for (int i = 0; i < count; i++) {
            if (books[i].title.equalsIgnoreCase(title))
                return books[i];
        }
        return null;
    }

    // Method Overloading - Search by Author
    public void searchBookByAuthor(String author) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (books[i].author.equalsIgnoreCase(author)) {
                books[i].display();
                found = true;
            }
        }
        if (!found)
            System.out.println("No books found by this author!");
    }

    public void borrowBook(String title) {
        Book book = searchBook(title);
        if (book == null) {
            System.out.println("Book not found!");
        } else if (!book.isAvailable) {
            System.out.println("Book already borrowed!");
        } else {
            book.isAvailable = false;
            System.out.println("You have borrowed the book successfully.");
        }
    }

    public void returnBook(String title) {
        Book book = searchBook(title);
        if (book == null) {
            System.out.println("Book not found!");
        } else if (book.isAvailable) {
            System.out.println("This book was not borrowed!");
        } else {
            book.isAvailable = true;
            System.out.println("Book returned successfully.");
        }
    }

    public void displayAllBooks() {
        if (count == 0) {
            System.out.println("No books in the library.");
            return;
        }
        System.out.println("\n--- All Books ---");
        for (int i = 0; i < count; i++) {
            books[i].display();
        }
    }
}

public class LibraryApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library(100);

        int choice;
        System.out.println("Welcome to the Library Management System!");

        do {
            System.out.println("\n1. Add a new book");
            System.out.println("2. Search for a book by title");
            System.out.println("3. Search for books by author");
            System.out.println("4. Borrow a book");
            System.out.println("5. Return a book");
            System.out.println("6. Display all books");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author name: ");
                    String author = sc.nextLine();
                    lib.addBook(new Book(title, author));
                    break;

                case 2:
                    System.out.print("Enter book title: ");
                    Book book = lib.searchBook(sc.nextLine());
                    if (book != null)
                        book.display();
                    else
                        System.out.println("Book not found!");
                    break;

                case 3:
                    System.out.print("Enter author name: ");
                    lib.searchBookByAuthor(sc.nextLine());
                    break;

                case 4:
                    System.out.print("Enter title of the book to borrow: ");
                    lib.borrowBook(sc.nextLine());
                    break;

                case 5:
                    System.out.print("Enter title of the book to return: ");
                    lib.returnBook(sc.nextLine());
                    break;

                case 6:
                    lib.displayAllBooks();
                    break;

                case 7:
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 7);

        sc.close();
    }
}
