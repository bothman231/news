package com.botham.db.resource;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.botham.domain.qa.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Integer> { 
	
    public List<Question> findAll();
    public List<Question> findAllByCategory(String category);
    public List<Question> findAllByCategoryAndSubCategory(String category, String subCategory);
    
}