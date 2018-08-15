package com.library.book.poc.dto;

public class Book {

    private String author;
    private String title;
    private String stock;

    public Book() {
    }

    public Book(String author, String title, String stock) {
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
