package com.botham.schedule;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.Properties;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.botham.base.BaseHelper;

@Configuration
@EnableScheduling
public class SchedulingConfiguration implements SchedulingConfigurer {

	 private static final Logger log = LoggerFactory.getLogger(SchedulingConfiguration.class);
  @Autowired
  ClientCheckin clientCheckin;

  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
	  String mName="configureTasks";
    taskRegistrar.addTriggerTask(() -> {
		try {
			
			clientCheckin.doIt();
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	},
        triggerContext -> {
        	Properties prop=null;
        	try {
				 prop = BaseHelper.getPropValues("c:\\unique\\", "A");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
          Integer delay=Integer.valueOf(prop.getProperty("clientSchedulerDelayInSecs"));
          log.info(mName+" "+"delay is "+delay);
          Instant nextTriggerTime = Instant.now().plus(delay, ChronoUnit.SECONDS);
          
          return Date.from(nextTriggerTime);
        });
  }
  
  
  
}