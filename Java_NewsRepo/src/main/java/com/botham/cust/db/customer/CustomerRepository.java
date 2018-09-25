package com.botham.cust.db.customer;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.botham.cust.domain.customer.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {

    public List<Customer> findAll();
    
    public Customer findByName(String search);
    
    public List<Customer> findByNameLike(String search);
    
    public List<Customer> findByNameLike(Pageable p, String search);
    
}