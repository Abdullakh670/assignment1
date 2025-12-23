import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    private List<Book> books;
    private Scanner scanner;

    public LibraryApp() {
        books = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print(" Select action:");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    printAllBooks();
                    break;
                case "2":
                    addNewBook();
                    break;
                case "3":
                    searchBooksByTitle();
                    break;
                case "4":
                    borrowBook();
                    break;
                case "5":
                    returnBook();
                    break;
                case "6":
                    deleteBookById();
                    break;
                case "7":
                    running = false;
                    System.out.println("Exit the application. ");
                    break;
                default:
                    System.out.println("Invalid input. Please try again. ");
            }
            System.out.println();
        }
        scanner.close();
    }

    private void printMenu() {
        System.out.println(" Welcome to the Library App!");
        System.out.println("Show all books ");
        System.out.println("Add a new book");
        System.out.println("Search for books by title ");
        System.out.println("Get a book");
        System.out.println(" Return a book");
        System.out.println("Delete a book by ID ");
        System.out.println("Exit ");
    }

    private void printAllBooks() {
        if (books.isEmpty()) {
            System.out.println(" There are no books in the library. ");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private void addNewBook() {
        try {
            System.out.print("Enter title: ");
            String title = scanner.nextLine();

            System.out.print(" Enter author:");
            String author = scanner.nextLine();

            System.out.print("Enter year of publication: ");
            int year = Integer.parseInt(scanner.nextLine());

            Book book = new Book(title, author, year);
            books.add(book);
            System.out.println("Book successfully added. ");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void searchBooksByTitle() {
        System.out.print("Please enter part of the title to search: ");
        String searchTerm = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(searchTerm)) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books with this title were found. ");
        }
    }

    private void borrowBook() {
        System.out.print("Please enter the book ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Book foundBook = findBookById(id);

            if (foundBook != null) {
                if (foundBook.isAvailable()) {
                    foundBook.markAsBorrowed();
                    System.out.println("Book successfully checked out. ");
                } else {
                    System.out.println(" Book already checked out.");
                }
            } else {
                System.out.println("Book with id= " + id + " not found. ");
            }
        } catch (NumberFormatException e) {
            System.out.println(" Invalid id format.");
        }
    }

    private void returnBook() {
        System.out.print("Please enter the book ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Book foundBook = findBookById(id);

            if (foundBook != null) {
                if (!foundBook.isAvailable()) {
                    foundBook.markAsReturned();
                    System.out.println("Book successfully returned. ");
                } else {
                    System.out.println(" Book already available.");
                }
            } else {
                System.out.println("Book with id= " + id + " not found. ");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid id format");
        }
    }

    private void deleteBookById() {
        System.out.print("Please enter the book ID to delete: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Book foundBook = findBookById(id);

            if (foundBook != null) {
                books.remove(foundBook);
                System.out.println(" Book successfully deleted.");
            } else {
                System.out.println("Book with id= " + id + " not found");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid id format. ");
        }
    }

    private Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        LibraryApp app = new LibraryApp();
        app.run();
    }
}
