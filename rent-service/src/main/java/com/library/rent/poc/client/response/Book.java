package com.library.rent.poc.client.response;

public class Book {

    private String author;
    private String title;
    private Integer stock;

    public Book() {
    }

    public Book(String author, String title, Integer stock) {
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
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
