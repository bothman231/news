package com.botham.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseHelper {
	
	private static final Logger log = LoggerFactory.getLogger(BaseHelper.class);
	
	public static String getBuildInfo() {
		String mName = "getBuildInfo";

		// Load info.txt as a property file and get date and time from it
		String buildInfoFile = "static/info.txt";
		InputStream resourceStream = BaseHelper.class.getClassLoader().getResourceAsStream(buildInfoFile);
		Properties props = new Properties();
		try {
			props.load(resourceStream);
		} catch (IOException e) {
			log.error(mName + " cant get build info " + buildInfoFile);
			//e.printStackTrace();
		}
		String build = props.getProperty("DateAndTimeOfBuild");
		
		if (log.isInfoEnabled()) {
			log.info(mName + " DateAndTimeOfBuild=" + build);
		}

		return build;
	}
}
