package com.library.rent.poc.output;

public class ReceiptOutput {

    private String receipt;

    public ReceiptOutput() {
    }

    public ReceiptOutput(String receipt) {
        this.receipt = receipt;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    @Override
    public String toString() {
        return "ReceiptOutput{" +
                "receipt='" + receipt + '\'' +
                '}';
    }
}
