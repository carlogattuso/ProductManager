package edu.upc.dsa;

import edu.upc.dsa.models.*;

import java.util.List;

public interface ProductManager {
    void addUser(String id, String nombre);
    void addProduct(String id, double precio);

    List<Product> orderedByPrice() throws EmptyProductListException;
    List<Product> orderedBySells() throws EmptyProductListException;

    List<Order> ordersByUser(String userId) throws EmptyOrderListException;

    Order addOrder(Order order) throws QueueFullException;
    Order getOrder() throws QueueEmptyException;
    List<OrderTOUser> sendOrdersByUser(List<Order> orders);
    User getUser(String id) throws UserNotFoundException;
    Product getProduct(String id) throws ProductNotFoundException;

    int userSize();
    int productSize();
    int orderSize();
}
