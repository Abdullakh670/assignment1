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
            System.out.print(" ");
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
                    System.out.println(" ");
                    break;
                default:
                    System.out.println(" ");
            }
            System.out.println();
        }
        scanner.close();
    }

    private void printMenu() {
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
    }

    private void printAllBooks() {
        if (books.isEmpty()) {
            System.out.println("  ");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private void addNewBook() {
        try {
            System.out.print(" ");
            String title = scanner.nextLine();

            System.out.print(" ");
            String author = scanner.nextLine();

            System.out.print(" ");
            int year = Integer.parseInt(scanner.nextLine());

            Book book = new Book(title, author, year);
            books.add(book);
            System.out.println(" ");
        } catch (Exception e) {
            System.out.println(" " + e.getMessage());
        }
    }

    private void searchBooksByTitle() {
        System.out.print(" ");
        String searchTerm = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(searchTerm)) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println(" ");
        }
    }

    private void borrowBook() {
        System.out.print(" ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Book foundBook = findBookById(id);

            if (foundBook != null) {
                if (foundBook.isAvailable()) {
                    foundBook.markAsBorrowed();
                    System.out.println(" ");
                } else {
                    System.out.println(" ");
                }
            } else {
                System.out.println(" " + id + "  ");
            }
        } catch (NumberFormatException e) {
            System.out.println(" ");
        }
    }

    private void returnBook() {
        System.out.print(" ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Book foundBook = findBookById(id);

            if (foundBook != null) {
                if (!foundBook.isAvailable()) {
                    foundBook.markAsReturned();
                    System.out.println(" ");
                } else {
                    System.out.println(" ");
                }
            } else {
                System.out.println(" " + id + "  ");
            }
        } catch (NumberFormatException e) {
            System.out.println(" ");
        }
    }

    private void deleteBookById() {
        System.out.print(" ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Book foundBook = findBookById(id);

            if (foundBook != null) {
                books.remove(foundBook);
                System.out.println(" ");
            } else {
                System.out.println(" " + id + " ");
            }
        } catch (NumberFormatException e) {
            System.out.println(" ");
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
