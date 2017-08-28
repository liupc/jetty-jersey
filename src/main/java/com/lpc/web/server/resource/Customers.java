package com.lpc.web.server.resource;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lpc.web.server.model.Customer;
import com.lpc.web.server.model.CustomerDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright 2017, Xiaomi.
 * All rights reserved.
 * Author: pengcheng@xiaomi.com
 */

@Api(value = "customers", description = "Restful Api for customers")
@Path("/customers")
public class Customers {
  private static final Logger LOG = LoggerFactory.getLogger(Customers.class);

  @Inject
  private CustomerDao customerDao;
  @ApiOperation(value = "Get all customers",
      notes = "Get all customers matching the given search string.",
      responseContainer = "List", response = Customer.class)
  @GET
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Customer> getCustomers(@ApiParam(value = "The search string is used to find the" +
      "customers. Not case sensetive", required = false, defaultValue ="") @QueryParam("search")
      String searchString, @ApiParam(value = "Limits of the result size", required = false,
      defaultValue = "10") @QueryParam("limit") int limit) throws IOException {
    List<Customer> customers = null;
    try {
      customers = customerDao.getCustomers(searchString, limit);
    } catch (Exception e) {
      LOG.error("Error encoutered while processing getCustomers: " + e.getMessage(), e);
      throw e;
    }
    System.out.println(customers);
//    return Response.ok(customers).build();
    return customers;
  }

  @ApiOperation(value = "Create a customer",
      notes = "Create a customer with the specified info"
  )
  @POST
  @Path("/create")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createCustomer(@ApiParam(value = "customer pojo", required = true) final
    Customer customer) throws Exception {
    Customer c = null;
    try {
      c = customerDao.createCustomer(customer.getId(), customer.getName(),
          customer.getAge(), customer.getSex());
      Response response = Response.created(getCustomerUriById(c.getId())).build();
      return response;
    } catch (Exception e) {
      String errorMsg = "Error encoutered while create customer: " + e.getMessage();
      LOG.error(errorMsg, e);
      throw e;
    }
  }

  private URI getCustomerUriById(String id) throws URISyntaxException {
    return new URI("/customer/" + id);
  }

}
