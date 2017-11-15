package com.botham;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class StartPoint {
	
    //public static JdbcTemplate jdbc = new JdbcTemplate();
    
    @Autowired
    static JdbcTemplate jdbc;
    
    private static final Logger log = LoggerFactory.getLogger(StartPoint.class);
    
	public static void main(String[] args) {
       doDbStuff();
       

	}
	
	public static void doDbStuff() {
	       jdbc.execute("Select * from dbo.questions");
	       log.debug("done");
	       

		}

}
