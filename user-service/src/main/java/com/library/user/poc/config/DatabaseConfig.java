package com.library.user.poc.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    private MongoClient mongoClient;

    public DatabaseConfig() {
        this.mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
    }

    public MongoCollection<Document> getMongoDatabase() {
        return mongoClient.getDatabase("user").getCollection("users");
    }
}
