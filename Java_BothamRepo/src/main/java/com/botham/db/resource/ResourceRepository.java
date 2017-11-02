package com.botham.db.resource;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.botham.domain.resource.Resource;


public interface ResourceRepository extends CrudRepository<Resource, String> { 
	
    public List<Resource> findAll();
    
}