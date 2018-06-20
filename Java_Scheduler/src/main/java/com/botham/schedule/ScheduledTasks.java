package com.botham.schedule;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// Very simple spring boot scheduler app,
// Runs every 5000 ms (5 secs).

//@ComponentScan(basePackages = {"com.botham.db.resource", "com.botham.*"})
@Component
public class ScheduledTasks {

	private final boolean useProxy = false;

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	// @Scheduled(fixedRate = 300000) // 300 secs or 5 mins
	// @Scheduled(fixedRate = 30000) // 30 seconds
	@Scheduled(fixedRate = 10000) // 10 seconds
	public void reportCurrentTime() throws Exception {

		String mName = "ScheduledTasks:reportCurrentTime";

		if (log.isInfoEnabled()) {
			
			log.info(mName + " The time is now {}", dateFormat.format(new Date()) + " ");
		}

	}

	String result = "";
	InputStream inputStream;

	
	//@Scheduled(fixedRateString = "${schedTime}") // 20 seconds"
	public void anotherTime() throws Exception {
		log.info("The time is now {}", dateFormat.format(new Date()));
		// anotherTime.
	}

}