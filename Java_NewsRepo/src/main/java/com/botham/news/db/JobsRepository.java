package com.botham.news.db;

import java.util.List;
import org.springframework.data.repository.Repository;
import com.botham.news.domain.Jobs;

//@Repository
public interface JobsRepository extends Repository<Jobs, String> {

    public List<Jobs> findAll();
    public Jobs findByName(String search);


}