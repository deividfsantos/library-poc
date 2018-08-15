package com.library.rent.poc.output;

public class StockOutput {

    private Integer stock;

    public StockOutput() {
    }

    public StockOutput(Integer stock) {
        this.stock = stock;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "StockOutput{" +
                "stock=" + stock +
                '}';
    }
}
