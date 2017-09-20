package com.botham.news.db;



import com.botham.news.domain.Jobs;

import java.util.List;

import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;




//@Repository
public interface JobsRepository extends Repository<Jobs, String> {

    public List<Jobs> findAll();

}