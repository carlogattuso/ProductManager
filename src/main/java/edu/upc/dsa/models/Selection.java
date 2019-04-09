package edu.upc.dsa.models;

public class Selection {
    private String idProduct;
    private int amount;

    public Selection() {
    }

    public Selection(String idProduct, int amount) {
        this.idProduct = idProduct;
        this.amount = amount;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
