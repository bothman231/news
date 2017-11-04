package com.botham.db.resource;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.botham.domain.resource.Resource;

@Repository
public interface ResourceRepository extends CrudRepository<Resource, String> { 
	
    public List<Resource> findAll();
    
}