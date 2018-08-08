package com.jinhyeon.services;

import java.util.List;

import com.jinhyeon.entities.Customer;

public interface CustomerService {
	List<Customer> getAllCustomers();
	Customer getNoCacheCustomer(Integer id);
	Customer getCacheCustomer(Integer id);
	void refresh(Integer id);
	Customer registerCustomer(Customer customer);
	Customer updateCustomer(Integer id, Customer customer);
	Customer deleteCustomer(Integer id);
}
