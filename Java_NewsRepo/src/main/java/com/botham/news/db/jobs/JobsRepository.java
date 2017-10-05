package com.botham.news.db.jobs;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.botham.news.domain.jobs.Jobs;


public interface JobsRepository extends CrudRepository<Jobs, Integer> {

    public List<Jobs> findAll();
    
    public Jobs findByName(String search);


}