package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private List<Order> orders = new LinkedList<>();

    public User() {
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order o){
        this.orders.add(o);
    }

    @Override
    public String toString() {
        return "User [id="+id+", name=" + name + ", orders=" + orders.size() +"]";
    }
}
