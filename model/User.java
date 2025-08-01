package model;

import java.util.ArrayList;

public class User {

    private int userId;
    private String fullName;
    private String username;
    private String password;
    private boolean isAdmin;

    private ArrayList<Integer> borrowedBooksIds = new ArrayList<>();

    public User(int userId, String fullName, String username, String password, boolean isAdmin, ArrayList<Integer> borrowedBooksIds) {
        this.userId = userId;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.borrowedBooksIds = borrowedBooksIds;
    }

    public int getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void addBorrowedBooksId(int bookId) {
        borrowedBooksIds.add(bookId);
    }

    public void removeBorrowedBooksId(int bookId) {
        if (borrowedBooksIds.contains(bookId)) {
            borrowedBooksIds.remove(bookId);
        }
    }

    public ArrayList<Integer> getBorrowedBooksId() {
        return borrowedBooksIds;
    }

    @Override
    public String toString() {
        return userId + " | " + fullName + " | " + " | " + username + " | " + password + " | " + isAdmin + " | " + borrowedBooksIds;

    }
}
