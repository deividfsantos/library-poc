package com.library.book.poc.output.v1;

public class BookOutput {

    private String author;
    private String title;
    private String stock;

    public BookOutput() {
    }

    public BookOutput(String author, String title, String stock) {
        this.author = author;
        this.title = title;
        this.stock = stock;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", stock='" + stock + '\'' +
                '}';
    }
}

