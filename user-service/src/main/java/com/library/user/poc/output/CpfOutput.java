package com.library.user.poc.output;

public class CpfOutput {

    private String cpf;

    public CpfOutput() {
    }

    public CpfOutput(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "CpfOutput{" +
                "cpf='" + cpf + '\'' +
                '}';
    }
}
