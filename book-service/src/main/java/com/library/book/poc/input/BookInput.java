package com.library.book.poc.input;

import javax.validation.constraints.NotEmpty;

public class BookInput {

    @NotEmpty
    private String author;

    @NotEmpty
    private String title;

    @NotEmpty
    private String stock;

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
        return "TitleOutput{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", stock='" + stock + '\'' +
                '}';
    }
}
