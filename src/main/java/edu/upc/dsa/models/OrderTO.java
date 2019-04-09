package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class OrderTO {
    private String userId;
    private List<Selection> selectionList = new LinkedList<>();

    public OrderTO() {
    }

    public OrderTO(String userId, List<Selection> selectionList) {
        this.userId = userId;
        this.selectionList = selectionList;
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
}
