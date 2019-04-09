package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class OrderTOUser {
    private int id;
    private List<Selection> selectionList = new LinkedList<>();

    public OrderTOUser() {
    }

    public OrderTOUser(int id, List<Selection> selectionList) {
        this.id = id;
        this.selectionList = selectionList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Selection> getSelectionList() {
        return selectionList;
    }

    public void setSelectionList(List<Selection> selectionList) {
        this.selectionList = selectionList;
    }
}
