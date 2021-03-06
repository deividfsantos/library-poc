package com.library.user.poc.input;

import javax.validation.constraints.NotEmpty;

public class UserInput {

    @NotEmpty
    private String name;

    @NotEmpty
    private String phone;

    @NotEmpty
    private String cpf;

    public UserInput() {
    }

    public UserInput(String name, String phone, String cpf) {
        this.name = name;
        this.phone = phone;
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "UserInput{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
