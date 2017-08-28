package com.lpc.web.server.model;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import com.lpc.web.server.CustomerExistException;
import jersey.repackaged.com.google.common.collect.Lists;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright 2017, Xiaomi.
 * All rights reserved.
 * Author: pengcheng@xiaomi.com
 */
@Singleton
@Service
public class CustomerDao {

  private static final Logger LOG = LoggerFactory.getLogger(CustomerDao.class);
  private static final List<Customer> customerStore = Lists.newLinkedList();

  static {
    customerStore.add(new Customer(17, "jong", Customer.Sex.MAN));
    customerStore.add(new Customer(44, "xiao", Customer.Sex.WOMAN));
  }

  public List<Customer> getCustomers(final String searchString, int limit) throws IOException {
    String toSearch = searchString == null ? "" : searchString;
    return Lists.newLinkedList(customerStore.stream().filter(c -> c.getName().contains(toSearch))
        .limit(limit).collect(Collectors.toList()));
  }

  public Customer createCustomer(String id, String name, int age, Customer.Sex sex)
      throws IOException {
    Customer c = new Customer(id, age, name, sex);
    if (customerStore.contains(c)) {
      LOG.info("Customer already exists: " + c.toString());
      throw new CustomerExistException("Customer already exist: " + c.toString());
    }
    customerStore.add(c);
    return c;
  }
}
