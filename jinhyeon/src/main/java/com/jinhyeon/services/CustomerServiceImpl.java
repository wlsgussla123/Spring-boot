package com.jinhyeon.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jinhyeon.constants.Constants;
import com.jinhyeon.entities.Customer;
import com.jinhyeon.repositories.CustomerRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> getAllCustomers() {
		return (List<Customer>) customerRepository.findAll();
	}
	
	@Override
	public Customer getNoCacheCustomer(Integer id) {
		slowQuery(2000);
		return customerRepository.findById(id)
				.orElseThrow(null);
	}

	@Override
	@Cacheable(value = Constants.CACHE_VALUE, key = "#id")
	public Customer getCacheCustomer(Integer id) {
		slowQuery(2000);
		return customerRepository.findById(id)
				.orElseThrow(null);
	}
	
	@Override
	@CacheEvict(value = Constants.CACHE_VALUE, key = "#id")
	public void refresh(Integer id) {
		log.info(id + ">>>> cache clear");
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
	
    // 빅쿼리를 돌린다는 가정
    private void slowQuery(long seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
