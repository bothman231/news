package com.botham.news.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.botham.news.domain.Db;

public interface DbRepository extends CrudRepository<Db, String> {

    public List<Db> findAll();
    
}