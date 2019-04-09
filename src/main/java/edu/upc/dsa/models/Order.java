package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class Order {
    private static int count = 0;
    private int id;
    private String userId;
    private List<Selection> selectionList = new LinkedList<>();

    public Order() {
    }

    public Order(Order o){
        this.userId = o.userId;
        this.selectionList = o.selectionList;
    }

    public Order(String userId, List<Selection> selectionList) {
        this.id = ++count;
        this.userId = userId;
        this.selectionList = selectionList;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Order.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Selection> getSelectionList() {
        return selectionList;
    }

    public void setSelectionList(List<Selection> selectionList) {
        this.selectionList = selectionList;
    }

    public String toString() {
        return "Order [id="+id+", userID=" + userId + ", selectionListSize=" + selectionList.size() +"]";
    }
}
