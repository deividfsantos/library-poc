package com.library.rent.poc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rents")
public class RentEntity {

    @Id
    private String code;
    private String cpf;
    private String bookName;

    public RentEntity() {
    }

    public RentEntity(String cpf, String bookName) {
        this.cpf = cpf;
        this.bookName = bookName;
    }

    public RentEntity(String code, String cpf, String bookName) {
        this.code = code;
        this.cpf = cpf;
        this.bookName = bookName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "RentEntity{" +
                "code='" + code + '\'' +
                ", cpf='" + cpf + '\'' +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
