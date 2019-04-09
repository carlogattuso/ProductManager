package edu.upc.dsa.services;


import edu.upc.dsa.ProductManager;
import edu.upc.dsa.ProductManagerImpl;
import edu.upc.dsa.models.EmptyProductListException;
import edu.upc.dsa.models.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/products", description = "Endpoint to Products Service")
@Path("/products")
public class ProductsService {
    private ProductManager pm;

    public ProductsService() {
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
        }
    }

    @GET
    @ApiOperation(value = "get all Products ordered by price", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
            @ApiResponse(code = 204, message = "No content", responseContainer="List")
    })
    @Path("/price")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsByPrice(){
        List<Product> productList;
        try {
            productList = this.pm.orderedByPrice();
        }
        catch(EmptyProductListException e){
            return Response.status(204).build();
        }
        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(productList) {
        };
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "get all Products ordered by sells", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
            @ApiResponse(code = 204, message = "No content", responseContainer="List")
    })
    @Path("/sells")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsBySells(){
        List<Product> productList;
        try {
            productList = this.pm.orderedBySells();
        }
        catch(EmptyProductListException e){
            return Response.status(204).build();
        }
        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(productList) {
        };
        return Response.status(201).entity(entity).build();
    }
}
