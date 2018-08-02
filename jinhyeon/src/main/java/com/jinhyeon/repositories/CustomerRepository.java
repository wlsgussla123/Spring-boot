package com.jinhyeon.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jinhyeon.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
