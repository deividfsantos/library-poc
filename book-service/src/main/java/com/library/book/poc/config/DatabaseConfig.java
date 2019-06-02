package com.library.book.poc.config;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    public MongoCollection<Document> mongoClient() {
        MongoClient mongoClient = new MongoClient(new ServerAddress("book-mongodb", 27017));
        return mongoClient.getDatabase("book").getCollection("books");
    }

}
