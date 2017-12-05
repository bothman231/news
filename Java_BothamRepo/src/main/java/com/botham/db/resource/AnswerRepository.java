package com.botham.db.resource;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.botham.domain.qa.Answer;
import com.botham.domain.qa.Question;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Integer> { 
	
    public List<Answer> findAll();
    
    public List<Answer> findAllByQuestionId(Integer questionId);
    
}