package com.botham.news.db.jobs;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.botham.news.domain.jobs.Jobs;

//public interface JobsRepository extends CrudRepository<Jobs, Integer> {
public interface JobsRepository extends PagingAndSortingRepository<Jobs, Integer> {

    public List<Jobs> findAll();
    
    public Jobs findByName(String search);
    
    public List<Jobs> findByNameLike(String search);
    
    public List<Jobs> findByNameLike(Pageable p, String search);
    
}