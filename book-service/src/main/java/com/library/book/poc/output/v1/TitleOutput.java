package com.library.book.poc.output.v1;

public class TitleOutput {

    private String title;

    public TitleOutput() {
    }

    public TitleOutput(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TitleOutput{" +
                "title='" + title + '\'' +
                '}';
    }
}
