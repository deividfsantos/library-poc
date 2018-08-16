package com.library.book.poc.repository;

import com.library.book.poc.dto.Book;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class BookRepository {

    private MongoCollection<Document> collection;

    public BookRepository(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    public String insert(Book book) {
        Document doc = new Document("title", book.getTitle())
                .append("author", book.getAuthor())
                .append("stock", book.getStock());

        collection.insertOne(doc);
        FindIterable<Document> documents = collection.find(doc);
        return documents.first().get("title").toString();
    }

    public List<Book> get() {
        List<Document> documents = collection.find().into(new ArrayList<>());
        return documents.stream()
                .map(doc -> new Book(doc.getString("author"), doc.getString("title"), doc.getString("stock")))
                .collect(Collectors.toList());
    }

    public Book getByTitle(String title) {
        Document docs = collection.find(new Document("title", title)).first();
        return Optional.ofNullable(docs)
                .map(doc -> new Book(doc.getString("author"), doc.getString("title"), doc.getString("stock")))
                .orElse(null);

    }

    public void update(Book book, String title) {
        Document doc = new Document("title", book.getTitle())
                .append("author", book.getAuthor())
                .append("stock", book.getStock());
        collection.updateOne(eq("title", title), new Document("$set", doc));
    }

    public void delete(String title) {
        collection.deleteOne(eq("title", title));
    }
}
