/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbs.customer.service;

import com.pbs.customer.database.CustomerDAO;
import com.pbs.customer.model.Customer;
import com.pbs.customer.model.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author Victor Arucha - varucha@hightech-corp.com - PBS
 */
@Path("/customer")
public class CustomerService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers() {
        List<Customer> customers = new ArrayList<Customer>();
        try {
            CustomerDAO customerDAO = new CustomerDAO();
            customers = customerDAO.findAll();

            if (customers.isEmpty()) {
                return Response.status(Status.NOT_FOUND.getStatusCode())
                        .entity(new ResponseEntity(Status.NOT_FOUND.getStatusCode(),
                                "Resources not found"))
                        .build();
            }

        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }

        return Response.status(Status.OK.getStatusCode()).entity(customers).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(@PathParam("id") int id) {
        Customer customer = null;
        try {
            CustomerDAO customerDAO = new CustomerDAO();
            customer = customerDAO.findById(id);

            if (customer == null) {
                return Response.status(Status.NOT_FOUND.getStatusCode())
                        .entity(new ResponseEntity(Status.NOT_FOUND.getStatusCode(),
                                "Resource " + id + " not found"))
                        .build();
            }

        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }

        return Response.status(Status.OK.getStatusCode()).entity(customer).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(Customer customer) {
        try {
            CustomerDAO customerDAO = new CustomerDAO();
            customerDAO.save(customer);

            if (customer.getId() == null) {
                return Response.status(Status.NOT_FOUND.getStatusCode())
                        .entity(new ResponseEntity(Status.NOT_FOUND.getStatusCode(),
                                "Resource " + customer.getId() + " not was created"))
                        .build();
            }

        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }

        return Response.status(Status.OK.getStatusCode()).entity(new ResponseEntity(Status.OK.getStatusCode(),
                                "Resource created with Id: " + customer.getId())).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(Customer customer) {
        try {
            if(customer.getId() == null){
                return Response.status(Status.CONFLICT.getStatusCode())
                        .entity(new ResponseEntity(Status.CONFLICT.getStatusCode(),
                                "Missing Id for resource"))
                        .build();
            }
            CustomerDAO customerDAO = new CustomerDAO();
            int update = customerDAO.update(customer);

            if (update == 0) {
                return Response.status(Status.NOT_FOUND.getStatusCode())
                        .entity(new ResponseEntity(Status.NOT_FOUND.getStatusCode(),
                                "Resource " + customer.getId() + " not found to update"))
                        .build();
            }

        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }

        return Response.status(Status.OK.getStatusCode()).entity(new ResponseEntity(Status.OK.getStatusCode(),
                                "Resource " + customer.getId() + " updated")).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletetCustomer(@PathParam("id") int id) {
        try {
            CustomerDAO customerDAO = new CustomerDAO();
            int delete = customerDAO.delete(id);

            if (delete == 0) {
                return Response.status(Status.NOT_FOUND.getStatusCode())
                        .entity(new ResponseEntity(Status.NOT_FOUND.getStatusCode(),
                                "Resource " + id + " not found to delete"))
                        .build();
            }

        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }

        return Response.status(Status.OK.getStatusCode()).entity(new ResponseEntity(Status.OK.getStatusCode(),
                                "Resource " + id + " delete")).build();
    }
}
