package com.botham.schedule;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.Properties;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.botham.base.BaseHelper;

@Configuration
@EnableScheduling
public class SchedulingConfiguration implements SchedulingConfigurer {

  @Autowired
  ClientCheckin clientCheckin;

  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
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
        	
          Instant nextTriggerTime = Instant.now().plus(Integer.valueOf(prop.getProperty("clientSchedulerDelayInSecs")), ChronoUnit.SECONDS);
          return Date.from(nextTriggerTime);
        });
  }
  
  
  
}