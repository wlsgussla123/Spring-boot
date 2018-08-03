package com.jinhyeon.services;

import java.util.List;

import com.jinhyeon.entities.Customer;

public interface CustomerService {
	List<Customer> getAllCustomers();
	Customer getCustomer(Integer id);
	Customer registerCustomer(Customer customer);
	Customer updateCustomer(Integer id, Customer customer);
}
