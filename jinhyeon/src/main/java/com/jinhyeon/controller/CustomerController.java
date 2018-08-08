package com.jinhyeon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jinhyeon.entities.Customer;
import com.jinhyeon.services.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@GetMapping(path = "/")
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}
	
	@GetMapping(path = "/nocache/{id}")
	public Customer getNoCacheCustomer(@PathVariable(value = "id") Integer id) {
		long start = System.currentTimeMillis();
		Customer customer = customerService.getNoCacheCustomer(id);
		long end = System.currentTimeMillis();
		
		log.info(">>>> nocache : " + Long.toString(end-start));
		
		return customer;
	}
	
	@GetMapping(path = "/cache/{id}")
	public Customer getCacheCustomer(@PathVariable(value = "id") Integer id) {
		long start = System.currentTimeMillis();
		Customer customer = customerService.getCacheCustomer(id);
		long end = System.currentTimeMillis();
		
		log.info(">>>> cache : " + Long.toString(end-start));

		return customer;
	}
	
	@GetMapping(path = "/refresh/{id}")
	public String refresh(@PathVariable(value = "id") Integer id) {
		customerService.refresh(id);
		return "cache clear";
	}
	
	@PostMapping(path = "/")
	public Customer registerCustomer(@RequestBody Customer customer) {
		return customerService.registerCustomer(customer);
	}
	
	@PutMapping(path = "/{id}")
	public Customer updateCustomer(@PathVariable(value = "id") Integer id, 
									@RequestBody Customer customer) {
		
		log.info(">>>>>>>>>>>> UPDATE " + id);
		return customerService.updateCustomer(id, customer);
	}
	
	@DeleteMapping("/{id}")
	public Customer deleteCustomer(@PathVariable(value = "id") Integer id) {
		return customerService.deleteCustomer(id);
	}
}
