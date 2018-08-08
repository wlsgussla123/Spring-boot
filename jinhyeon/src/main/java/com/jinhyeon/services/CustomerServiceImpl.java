package com.jinhyeon.services;

import java.util.List;

import javax.swing.text.StyleConstants.ColorConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jinhyeon.constants.Constants;
import com.jinhyeon.entities.Customer;
import com.jinhyeon.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	@Cacheable(value = Constants.CACHE_CUSTOMER)
	public List<Customer> getAllCustomers() {
		return (List<Customer>) customerRepository.findAll();
	}
	
	@Override
	public Customer getCustomer(Integer id) {
		return customerRepository.findById(id)
				.orElseThrow(null);
	}

	@Override
	public Customer registerCustomer(Customer customer) {
		return customerRepository.save(customer); 
	}
	
	@Override
	public Customer updateCustomer(Integer id, Customer customer) {
		Customer updatedCustomer = customerRepository.findById(id)
								.orElse(new Customer());
		
		updatedCustomer.setFirstName(customer.getFirstName());
		updatedCustomer.setLastName(customer.getLastName());
		
		return customerRepository.save(updatedCustomer);
	}

	@Override
	public Customer deleteCustomer(Integer id) {
		Customer customer = customerRepository.findById(id)
								.orElse(new Customer());
		
		if(customer == null) {
			return null;
		} else {
			customerRepository.delete(customer);
			return customer;
		}
	}
}
