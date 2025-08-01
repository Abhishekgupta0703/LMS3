package manager;

import java.io.*;
import java.util.*;
import model.*;

public class LibraryManager {

    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    public void addBook(Book b) {
        books.add(b);
    }

    public void addUser(User u) {
        users.add(u);
    }

    public Book getBookById(int bookId) {
        for (Book b : books) {
            if (b.getId() == bookId) {
                return b;
            }
        }
        return null;
    }

    public User getUserById(int UserId) {
        for (User u : users) {
            if (u.getUserId() == UserId) {
                return u;
            }
        }
        return null;
    }

    public void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library");
        } else {
            for (Book b : books) {
                System.out.println(b.getId() + " | " + b.getTitle() + " | " + b.getAuthor() + " | " + b.getPublisher() + " | " + b.getYear() + " | " + b.getAvailbleCopies() + "/" + b.getTotalCopies());
            }
        }
    }

    public void searchBookByTitle(String title) {
        title = title.toLowerCase();
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(title)) {
                System.out.println(b);
            }
        }
    }

    public void borrowBook(int bookId, int userId) {
        User u = getUserById(userId);
        Book b = getBookById(bookId);

        if (u != null && b != null && b.getAvailbleCopies() > 0) {
            b.decreaseCopies();
            u.addBorrowedBooksId(bookId);
            System.out.println("Book borrowed Successfully");
        } else {
            System.out.println("Unable to Borrow the book");
        }
    }

    public void returnBook(int bookId, int userId) {
        User u = getUserById(userId);
        Book b = getBookById(bookId);
        if (u != null && b != null) {
            b.increaseCopies();
            u.removeBorrowedBooksId(bookId);
            System.out.println("Book returned Successfully");
        } else {
            System.out.println("Unable to return the book");
        }
    }

    public ArrayList<User> getAllUsers() {
        return users;
    }

    public void saveUsersToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("users.txt"))) {
            for (User u : users) {
                StringBuilder borrowedIds = new StringBuilder();
                for (int id : u.getBorrowedBooksId()) {
                    borrowedIds.append(id).append(";");
                }
                pw.println(u.getUserId() + "," + u.getFullName() + "," + u.getUserName() + "," + u.getPassword() + "," + u.getIsAdmin() + "," + borrowedIds);
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    public void loadUsersFromFile() {
        users.clear();
        File file = new File("users.txt");
        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", 6);
                int userId = Integer.parseInt(data[0]);
                String fullName = data[1];
                String username = data[2];
                String password = data[3];
                boolean isAdmin = Boolean.parseBoolean(data[4]);

                ArrayList<Integer> borrowedIds = new ArrayList<>();
                if (data.length == 6 && !data[5].isEmpty()) {
                    String[] ids = data[5].split(";");
                    for (String id : ids) {
                        if (!id.isEmpty()) {
                            borrowedIds.add(Integer.parseInt(id));
                        }
                    }
                }

                users.add(new User(userId, fullName, username, password, isAdmin, borrowedIds));
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    public void saveBooksToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("books.txt"))) {
            for (Book b : books) {
                pw.println(b.getId() + "," + b.getTitle() + "," + b.getAuthor() + "," + b.getPublisher() + "," + b.getYear() + "," + b.getAvailbleCopies() + "," + b.getTotalCopies());
            }
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    public void loadBooksFromFile() {
        books.clear();
        File file = new File("books.txt");
        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String title = data[1];
                String author = data[2];
                String publisher = data[3];
                int year = Integer.parseInt(data[4]);
                int available = Integer.parseInt(data[5]);
                int total = Integer.parseInt(data[6]);
                books.add(new Book(id, title, author, publisher, year, available, total));
            }
        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
    }

}
