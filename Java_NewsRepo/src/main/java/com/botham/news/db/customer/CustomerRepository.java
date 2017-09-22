package com.botham.news.db.customer;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.botham.news.domain.customer.Customer;




public interface CustomerRepository extends CrudRepository<Customer, String> { 
	
    public List<Customer> findAll();
    
    
}