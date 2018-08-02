package com.jinhyeon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jinhyeon.entities.Customer;
import com.jinhyeon.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@GetMapping(path = "/all")
	public List<Customer> getAllCustomers() {
		System.out.println("test");
		return customerService.getAllCustomers();
	}
}
