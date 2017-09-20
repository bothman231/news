package com.botham.news.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class JobsRepositoryTest {

    @Autowired
    private JobsRepository jobsRepository;
 
    // write test cases here
    
    @Test
    public void whenFindByName_thenReturnJob() {
    	//jobsRepository.findByName("a");
    	jobsRepository.findAll();
    }
    
 
}
