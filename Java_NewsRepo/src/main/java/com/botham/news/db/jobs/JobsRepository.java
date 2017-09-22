package com.botham.news.db.jobs;

import java.util.List;
import org.springframework.data.repository.Repository;

import com.botham.news.domain.jobs.Jobs;


public interface JobsRepository extends Repository<Jobs, String> {

    public List<Jobs> findAll();
    
    public Jobs findByName(String search);


}