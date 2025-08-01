
import java.util.*;
import manager.*;
import model.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LibraryManager library = new LibraryManager();
        LoginManager login = new LoginManager();
        library.loadUsersFromFile();
        library.loadBooksFromFile();

        // library.addUser(new User(1001, "Admin", "admin", "admin123", true, new ArrayList<Integer>()));
        // library.addUser(new User(1002, "Abhishek", "Abhi", "asdf123", false, new ArrayList<Integer>()));

        // library.addBook(new Book(101, "Basic of Java", "Mishra", "ABCPRess", 2023, 10, 10));
        // library.addBook(new Book(102, "Python", "Sharma", "Ganga Press", 2024, 3, 3));

        User currentUser = null;
        while (currentUser == null) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter your username: ");
                    String username = sc.next();
                    System.out.print("Enter your password: ");
                    String password = sc.next();
                    currentUser = login.userLogin(username, password, library.getAllUsers());
                    if (currentUser == null) {
                        System.out.println("Unable to login");
                    } else {
                        System.out.println("Login successful");
                    }
                }
                case 2 -> {
                    sc.nextLine(); // consume leftover newline
                    System.out.print("Enter your Name: ");
                    String fullName = sc.nextLine();
                    System.out.print("Enter your username: ");
                    String username = sc.next();
                    System.out.print("Enter your password: ");
                    String password = sc.next();
                    int userId = 1000 + (int) (Math.random() * 9000);
                    User newUser = new User(userId, fullName, username, password, false, new ArrayList<Integer>());
                    library.addUser(newUser);
                    library.saveUsersToFile();

                    System.out.println("User Registered Successfully ");
                    System.out.println("User can now login");
                }
                case 3 -> {
                    System.out.println("Exiting the application ");
                    return;
                }
                default -> {
                    System.out.println("Invalid choice ");
                }
            }
        }
        boolean isAdmin = currentUser.getIsAdmin();
        System.out.print("Welcome " + currentUser.getFullName());
        if (isAdmin) {
            System.out.println(" | Admin ");
        }
        int choice;
        do {

            System.out.println("");

            System.out.println("=====Menu====");
            if (isAdmin) {
                System.out.println("1. View Book");
                System.out.println("2. Add Book");
                System.out.println("3. View Users");
                System.out.println("4. Logout");
                choice = sc.nextInt();
                switch (choice) {
                    case 1 -> {
                        System.out.println("=====Book List====");
                        library.viewAllBooks();
                    }
                    case 2 -> {

                        sc.nextLine();
                        System.out.print("Enter book title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter Author name: ");
                        String author = sc.nextLine();
                        System.out.print("Enter book publisher:");
                        String publisher = sc.nextLine();
                        System.out.print("Enter book publication year: ");
                        int year = sc.nextInt();
                        System.out.print("Enter number of copies: ");
                        int copies = sc.nextInt();

                        int bookId = 100 + (int) (Math.random() * 900);
                        Book newBook = new Book(bookId, title, author, publisher, year, copies, copies);
                        library.addBook(newBook);
                        library.saveBooksToFile();

                        System.out.println("Book added Successfully");
                    }
                    case 3 -> {
                        System.out.println("=====User List====");
                        for (User u : library.getAllUsers()) {
                            System.out.println(u.getUserId() + " | " + u.getFullName() + " | " + (u.getIsAdmin() ? "Admin" : "Normal User"));
                        }
                    }
                    case 4 -> {
                        System.out.println("Logging out...");
                    }

                    default -> {
                        System.out.println("Invalid choice");
                    }

                }
            } else {
                System.out.println("1. View Book");
                System.out.println("2. Search Book");
                System.out.println("3. Borrow Book");
                System.out.println("4. Return Book");
                System.out.println("5. My Borrowed Books");
                System.out.println("6. Logout");
                choice = sc.nextInt();
                switch (choice) {
                    case 1 -> {
                        System.out.println("=====Book List====");
                        library.viewAllBooks();
                    }
                    case 2 -> {
                        System.out.print("Enter book title: ");
                        String title = sc.nextLine();
                        library.searchBookByTitle(title);
                    }
                    case 3 -> {
                        System.out.print("Enter book Id: ");
                        int bookId = sc.nextInt();
                        library.borrowBook(bookId, currentUser.getUserId());
                        library.saveBooksToFile();
                        library.saveUsersToFile();

                    }
                    case 4 -> {
                        System.out.print("Enter book Id: ");
                        int bookId = sc.nextInt();
                        library.returnBook(bookId, currentUser.getUserId());
                        library.saveBooksToFile();
                        library.saveUsersToFile();

                    }
                    case 5 -> {
                        System.out.println("=====My Borrowed Books====");

                        System.out.println(currentUser.getBorrowedBooksId());
                        library.saveUsersToFile();
                    }
                    case 6 -> {
                        System.out.println("Logging out...");
                        return;
                    }
                    default -> {
                        System.out.println("Invalid choice");
                    }
                }


            }
        } while (choice != (currentUser.getIsAdmin() ? 4 : 6));
        // library.saveUsersToFile();
        sc.close();

    }
}
