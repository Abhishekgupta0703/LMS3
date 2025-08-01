package model;

public class Book {

    private int id;
    private String title;
    private String author;
    private String publisher;
    private int year;
    private int availbleCopies;
    private int totalCopies;

    public Book(int id, String title, String author, String publisher, int year, int availbleCopies, int totalCopies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.availbleCopies = availbleCopies;
        this.totalCopies = totalCopies;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public int getYear() {
        return this.year;
    }

    public int getAvailbleCopies() {
        return this.availbleCopies;
    }

    public int getTotalCopies() {
        return this.totalCopies;
    }

    public void decreaseCopies() {
        if (availbleCopies > 0) {
            this.availbleCopies--;
        }
    }

    public void increaseCopies() {
        this.availbleCopies++;
    }
}
