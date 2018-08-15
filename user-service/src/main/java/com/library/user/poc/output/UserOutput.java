package com.library.user.poc.output;

public class UserOutput {
    private String cpf;
    private String name;
    private String phone;


    public UserOutput() {
    }

    public UserOutput(String name, String phone, String cpf) {
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
        return "UserOutput{" +
                "cpf='" + cpf + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
