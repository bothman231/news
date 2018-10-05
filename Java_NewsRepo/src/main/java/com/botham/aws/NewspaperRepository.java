package com.botham.aws;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.botham.domain.newspaper.Newspaper;

public interface NewspaperRepository extends PagingAndSortingRepository<Newspaper, String> {

    public List<Newspaper> findAll();
    
    public Newspaper findByNewsId(String search);
    
    public Newspaper getByNewsId(String search);
    
    public Newspaper getByNewsNumber(String search);
    
}