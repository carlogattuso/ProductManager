package edu.upc.dsa.services;


import edu.upc.dsa.ProductManager;
import edu.upc.dsa.ProductManagerImpl;
import edu.upc.dsa.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/orders", description = "Endpoint to Orders Service")
@Path("/orders")
public class OrdersService {
    private ProductManager pm;

    public OrdersService() throws QueueFullException {
        this.pm = ProductManagerImpl.getInstance();
        if (pm.productSize() == 0) {
            pm.addUser("carlo","Carlo");
            pm.addUser("mario","Mario");

            pm.addProduct("bigmac", 2);
            pm.addProduct("patatasmedianas", 1.5);
            pm.addProduct("cocacola", 1);

            pm.addProduct("cbo", 3);
            pm.addProduct("patataspequeñas", 1);
            pm.addProduct("fanta", 1);

            pm.addProduct("mcpollo", 1.5);
            pm.addProduct("patatasdeluxe", 1);
            pm.addProduct("cerveza", 1);

            List<Selection> selectionList = new ArrayList<>();
            Selection s;
            s= new Selection("bigmac",1);
            selectionList.add(s);
            s= new Selection("patataspequeñas",1);
            selectionList.add(s);
            pm.addOrder(new Order("carlo",selectionList));
        }
    }

    @GET
    @ApiOperation(value = "get the next Order", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Order.class),
            @ApiResponse(code = 204, message = "No orders")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNextOrder() {
        Order o;
        try{
            o = this.pm.getOrder();
        }
        catch(QueueEmptyException e){
            return Response.status(204).build();
        }

        GenericEntity<Order> entity = new GenericEntity<Order>(o) {
        };
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "get orders by User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = OrderTOUser.class),
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdersByUser(@PathParam("id") String id) {
        List<Order> orderList;
        try {
            User u = this.pm.getUser(id);
            orderList = this.pm.ordersByUser(id);
        }
        catch (EmptyOrderListException e1){
            return Response.status(204).build();
        }
        catch (UserNotFoundException e2){
            return Response.status(404).build();
        }
        GenericEntity<List<OrderTOUser>> entity = new GenericEntity<List<OrderTOUser>>(this.pm.sendOrdersByUser(orderList)) {
        };
        return Response.status(201).entity(entity).build();
    }

    @POST
    @ApiOperation(value = "create a new Order", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= OrderTO.class),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Product not found"),
            @ApiResponse(code = 501, message = "Queue full")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newOrder(OrderTO order) {
        Order o;
        try{
            this.pm.getUser(order.getUserId());
            for(Selection s : order.getSelectionList()){
                this.pm.getProduct(s.getIdProduct());
            }
            o = new Order(order.getUserId(),order.getSelectionList());
            this.pm.addOrder(o);
        }
        catch(UserNotFoundException e1){
            return Response.status(404).build();
        }
        catch(ProductNotFoundException e2){
            return Response.status(500).build();
        }
        catch(QueueFullException e3){
            return Response.status(501).build();
        }
        GenericEntity<Order> entity = new GenericEntity<Order>(o) {
        };
        return Response.status(201).entity(entity).build();
    }

}
