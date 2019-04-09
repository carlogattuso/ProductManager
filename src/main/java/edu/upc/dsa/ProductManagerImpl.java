package edu.upc.dsa;

import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;

import java.util.*;

public class ProductManagerImpl implements ProductManager{
    private static ProductManagerImpl instance = new ProductManagerImpl();

    final static Logger logger = Logger.getLogger(ProductManagerImpl.class);

    public static ProductManagerImpl getInstance() {
        return instance;
    }

    private List<Product> products;
    private HashMap<String, User> userHashMap;
    private Queue<Order> orderQueue;

    private ProductManagerImpl() {
        this.products = new LinkedList<>();
        this.userHashMap = new HashMap<>();
        this.orderQueue = new QueueImpl<>(20);
    }

    public void addUser(String id, String name){
        User u = new User(id,name);
        userHashMap.put(id,u);
        logger.info("New user: "+u.toString());
        logger.info("Users: "+userSize());
    }

    public void addProduct(String id, double price){
        Product p = new Product(id,price);
        this.products.add(p);
        logger.info("New product: "+p.toString());
        logger.info("Products: "+productSize());
    }

    public Order addOrder(Order o) throws QueueFullException {
        this.orderQueue.push(o);
        logger.info("New Order: "+o.toString());
        logger.info("Orders: "+ orderQueue.size());
        return o;
    }

    public Order getOrder () throws QueueEmptyException {
        Order o = this.orderQueue.pop();
        for(Selection s: o.getSelectionList()){
            for(Product p : products){
                if(s.getIdProduct().equals(p.getId()))
                p.addSell(s.getAmount());
            }
        }
        this.userHashMap.get(o.getUserId()).addOrder(o);
        logger.info("Get order: "+o.toString());
        logger.info("Orders: "+ orderQueue.size());
        return o;
    }

    public Product getProduct(String id) throws ProductNotFoundException {
        for(Product p : this.products){
            if(p.getId().equals(id)){
                Product found = p;
                logger.info("Get product: "+p.toString());
                return found;
            }
        }
        logger.warn("Product not found");
        throw new ProductNotFoundException("Product not found");
    }

    public User getUser(String id) throws UserNotFoundException {
        User u = this.userHashMap.get(id);
        if(u==null){
            logger.warn("User not found");
            throw new UserNotFoundException("User not found");
        }
        else{
            logger.info("Get user: "+u.toString());
            return u;
        }
    }

    public List<Order> ordersByUser (String id) throws EmptyOrderListException {
        List<Order> orderList = this.userHashMap.get(id).getOrders();
        if(orderList.size()==0){
            logger.warn("Empty order list");
            throw new EmptyOrderListException("Empty order list");
        }
        logger.info("Orders by user: User ID:"+id+" Orders:"+orderList.size());
        return orderList;
    }

    public List<Product> orderedByPrice () throws EmptyProductListException {
        if(this.productSize()==0){
            logger.warn("Empty product list");
            throw new EmptyProductListException("Empty product list");
        }
        List<Product> ordered = new LinkedList<>();
        for(Product p : this.products){
            ordered.add(new Product(p));
        }
        Collections.sort(ordered);
        logger.info("Orders by price: Orders:"+ordered.size());
        return ordered;
    }

    public List<Product> orderedBySells () throws EmptyProductListException {
        if(productSize()==0){
            logger.warn("Empty order list");
            throw new EmptyProductListException("Empty product list");
        }
        List<Product> ordered = new LinkedList<>();
        for(Product p : this.products){
            ordered.add(new Product(p));
        }
        Collections.sort(ordered, new Comparator<Product>() {
            @Override
            public int compare(Product one, Product other) {
                return Double.compare(other.getSells(), one.getSells());
            }
        });
        logger.info("Orders by sells: Orders:"+ordered.size());
        return ordered;
    }

    public int userSize(){
        return this.userHashMap.size();
    }

    public int productSize(){
        return this.products.size();
    }

    public List<OrderTOUser> sendOrdersByUser(List<Order> orders){
        List<OrderTOUser> list = new ArrayList<>();
        for(Order o : orders){
            list.add(new OrderTOUser(o.getId(),o.getSelectionList()));
        }
        return list;
    }

    public int orderSize(){
        return this.orderQueue.size();
    }
}
