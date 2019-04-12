package edu.upc.dsa;

import edu.upc.dsa.models.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class ProductManagerTest {

    private ProductManagerImpl pm;

    @Before
    public void initialize() throws QueueFullException{
        pm = ProductManagerImpl.getInstance();

        pm.addUser("carlo","Carlo");
        pm.addUser("mario","Mario");

        pm.addProduct("bigmac", 2);
        pm.addProduct("patatasmedianas", 1.5);
        pm.addProduct("cocacola", 1);

        pm.addProduct("cbo", 3);
        pm.addProduct("patataspequeñas", 1);
        pm.addProduct("fanta", 1);

        List<Selection> selections = new LinkedList<>();
        selections.add(new Selection("cbo",1));
        selections.add(new Selection("patataspequeñas",2));
        selections.add(new Selection("cocacola",1));
        Order o = new Order("carlo",selections);
        this.pm.addOrder(o);
    }

    @After
    public void tearDown(){
        pm.clear();
    }

    @Test
    public void addOrderTest() throws QueueFullException {
        List<Selection> selections = new LinkedList<>();
        selections.add(new Selection("cbo",1));
        selections.add(new Selection("patatasmedianas",1));
        selections.add(new Selection("fanta",3));
        Order o = new Order("carlo",selections);
        this.pm.addOrder(o);
        Assert.assertEquals(2,this.pm.orderSize());
    }

    @Test
    public void getOrderTest() throws QueueFullException, QueueEmptyException, ProductNotFoundException {
        Order o2 = this.pm.getOrder();
        Assert.assertEquals(3,o2.getId());
        Assert.assertEquals("carlo",o2.getUserId());
        Assert.assertEquals(0,this.pm.orderSize());
        Assert.assertEquals(1,this.pm.getProduct("cbo").getSells());
        Assert.assertEquals(2,this.pm.getProduct("patataspequeñas").getSells());
        Assert.assertEquals(1,this.pm.getProduct("cocacola").getSells());
    }
}
