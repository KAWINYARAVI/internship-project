import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrow() {
        this.isBorrowed = true;
    }

    public void returnBook() {
        this.isBorrowed = false;
    }

    @Override
    public String toString() {
        return "Book [title=" + title + ", author=" + author + ", isBorrowed=" + isBorrowed + "]";
    }
}

class User {
    private String name;
    private ArrayList<Book> borrowedBooks;

    public User(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.borrow();
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.returnBook();
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", borrowedBooks=" + borrowedBooks + "]";
    }
}

class Library {
    private HashMap<String, Book> books;
    private HashMap<String, User> users;

    public Library() {
        this.books = new HashMap<>();
        this.users = new HashMap<>();
    }

    public void addBook(String title, String author) {
        books.put(title, new Book(title, author));
    }

    public void registerUser(String name) {
        users.put(name, new User(name));
    }

    public void borrowBook(String userName, String bookTitle) {
        User user = users.get(userName);
        Book book = books.get(bookTitle);

        if (user != null && book != null && !book.isBorrowed()) {
            user.borrowBook(book);
            System.out.println(userName + " borrowed " + bookTitle);
        } else {
            System.out.println("Book is not available or user does not exist.");
        }
    }

    public void returnBook(String userName, String bookTitle) {
        User user = users.get(userName);
        Book book = books.get(bookTitle);

        if (user != null && book != null && book.isBorrowed()) {
            user.returnBook(book);
            System.out.println(userName + " returned " + bookTitle);
        } else {
            System.out.println("Book is not borrowed or user does not exist.");
        }
    }

    public void viewBooks() {
        System.out.println("Books in library:");
        for (Book book : books.values()) {
            System.out.println(book);
        }
    }

    public void viewUsers() {
        System.out.println("Registered users:");
        for (User user : users.values()) {
            System.out.println(user);
        }
    }

    public void viewUserBorrowedBooks(String userName) {
        User user = users.get(userName);
        if (user != null) {
            System.out.println(userName + " has borrowed:");
            for (Book book : user.getBorrowedBooks()) {
                System.out.println(book);
            }
        } else {
            System.out.println("User does not exist.");
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Library Management System");
        System.out.println("Commands: addBook, registerUser, borrowBook, returnBook, viewBooks, viewUsers, viewUserBorrowedBooks, exit");

        while (true) {
            System.out.print("Enter command: ");
            command = scanner.nextLine();

            switch (command) {
                case "addBook":
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    library.addBook(title, author);
                    break;
                case "registerUser":
                    System.out.print("Enter user name: ");
                    String name = scanner.nextLine();
                    library.registerUser(name);
                    break;
                case "borrowBook":
                    System.out.print("Enter user name: ");
                    String userName = scanner.nextLine();
                    System.out.print("Enter book title: ");
                    String bookTitle = scanner.nextLine();
                    library.borrowBook(userName, bookTitle);
                    break;
                case "returnBook":
                    System.out.print("Enter user name: ");
                    userName = scanner.nextLine();
                    System.out.print("Enter book title: ");
                    bookTitle = scanner.nextLine();
                    library.returnBook(userName, bookTitle);
                    break;
                case "viewBooks":
                    library.viewBooks();
                    break;
                case "viewUsers":
                    library.viewUsers();
                    break;
                case "viewUserBorrowedBooks":
                    System.out.print("Enter user name: ");
                    userName = scanner.nextLine();
                    library.viewUserBorrowedBooks(userName);
                    break;
                case "exit":
                    System.out.println("Exiting Library Management System.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }
    }
}
