package com.botham.rest.test;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.botham.news.db.jobs.JobsRepository;



// Easy gotcha's
// 1 Your test class must have *Test or Test* in the name
// 2 Your test class should be in a src/test/java/ folder structure
// 3 test is reserved, dont use it anywhere else in the pom etc

// Though you can fix method order as shown hear, it is considered bad practice
// as unit tests should be completely independent and run consistently each time 


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

	   Logger log = LoggerFactory.getLogger(ApplicationTest.class);
		
	   
	   @Autowired
	   JobsRepository jobsRepository;
	   
	   private boolean firstTimeStart=true;

	public boolean isFirstTimeStart() {
		return firstTimeStart;
	}

	public void setFirstTimeStart(boolean firstTimeStart) {
		this.firstTimeStart = firstTimeStart;
	}

	@Before
	public void start() {
		if (!firstTimeStart) { return; }
		
		firstTimeStart=false;
		
		for (int x=0;x<3;x++) {
			log.debug("************ STARTS ****************");
		}
	}
	
    @Test
    public void cDoTest() throws Exception {
    	String mName="cDo";
        log.debug(mName+" ");


  
    }
    
    @Test
    public void acontextLoads() throws Exception {
    	String mName="acontextLoads";
        log.debug(mName+" ");
        log.debug(""+jobsRepository);
        //assert(jobsRepository).getClass();
    }
    
    @Test
    public void bfindAllTestPass() throws Exception {
    	String mName="findAllTest";
    	int jobs=jobsRepository.findAll().size();
        log.debug(mName+" Jobs="+jobs);
        assert(jobs>0);

        
    }
    
    //@Test
    public void bfindAllTestFail() throws Exception {
    	String mName="findAllTest";
    	int jobs=jobsRepository.findAll().size();
        log.debug(mName+" Jobs="+jobs);
        assert(jobs>50);

        
    }
    
	
	@After
	public void end() {
		for (int x=0;x<3;x++) {
			log.debug("************ ENDS ****************");
		}
	}

}