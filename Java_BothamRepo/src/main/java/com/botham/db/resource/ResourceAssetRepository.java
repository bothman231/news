package com.botham.db.resource;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.botham.domain.resource.Resource;
import com.botham.domain.resource.ResourceAsset;

@Repository
public interface ResourceAssetRepository extends CrudRepository<ResourceAsset, String> { 
	
    public List<ResourceAsset> findAll();
    
}