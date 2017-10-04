package com.botham.rest.jobs;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;
import org.springframework.transaction.annotation.Transactional;

import com.botham.base.GlobalConstants;
import com.botham.news.db.customer.CustomerRepository;
import com.botham.news.domain.customer.Customer;



public class CustomerHelper {
	
	private CustomerHelper() {
		
	}
	
	
	   static Logger log = LoggerFactory.getLogger(CustomerHelper.class);
	
	   @Autowired
	   static CustomerRepository customerRepository;
	   
	   
	   @Transactional("customerTransactionManager")
	   //@AliasFor(value="customerTransactionManager")
			   public static void getCustomers() {
		   String mName="getCustomers";
		   if (log.isDebugEnabled()) {
			   log.debug(mName+GlobalConstants.STARTS);
		   }
		   
	       List<Customer> clist = customerRepository.findAll();
	       
	       for (Customer c : clist) {
		      if (log.isDebugEnabled()) {
		    	  log.debug(mName+" "+c.getId());
		      }
	       }

	   }
}
