package edu.upc.dsa.models;

public class Product implements Comparable<Product>{
    private String id;
    private Double price;
    private int sells;

    public Product() {
    }

    public Product (Product p){
        this.id = p.getId();
        this.price = p.getPrice();
        this.sells = p.getSells();
    }

    public Product(String id, double price) {
        this.id = id;
        this.price = price;
        this.sells = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSells() {
        return sells;
    }

    public void setSells(int sells) {
        this.sells = sells;
    }

    public void addSell(int amount){
        this.sells+=amount;
    }

    public int compareTo(Product p) {
        return this.price.compareTo(p.getPrice());
    }

    @Override
    public String toString() {
        return "Product [id="+id+", price=" + price + ", sells=" + sells +"]";
    }
}
