package com.botham;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// Very simple spring boot scheduler app,
// Runs every 5000 ms (5 secs).

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //@Scheduled(fixedRate = 300000)  // 5 mins
    @Scheduled(fixedRate = 30000)  // 30 seconds
    public void reportCurrentTime() throws Exception {
        String mName="reportCurrentTime";
        
        String systemName=System.getenv("B_SYSTEM_NAME");
        log.debug(mName+"B_SYSTEM_NAME="+systemName);
        String configRoot=System.getenv("B_CONFIG_ROOT");        
        log.debug(mName+"B_CONFIG_ROOT="+configRoot);
        
        if (configRoot==null) {
        	if (log.isErrorEnabled()) {
        	   log.error(mName+" B_CONFIG_ROOT is not set");
        	}
        	return;
        }  else {
        	log.debug(mName+" B_CONFIG_ROOT="+configRoot);
        }
        
        
        

        
// This seems to work on remote Tomcat server but not inside STS?        
        String instance=System.getProperty("instance");
        // String node=System.getProperty("node"); // Comes from config.properties
        String node="";
        
        if (instance!=null) {
        	Properties prop = getPropValues(configRoot, instance);
            node=prop.getProperty("node");            
        }
        

        log.info("The time is now {}", dateFormat.format(new Date())+" "+systemName+" instance="+instance+" node="+node);
        
    }
    
    
 
    	String result = "";
    	InputStream inputStream;
     
    	public Properties getPropValues(String configRoot, String instance) throws IOException, Exception {
    		String mName="getPropValues";
			Properties prop = new Properties();
    		try {

    			
    			//String propFileName1 = "\\unique\\JAVA_CONF\\A\\config.properties";
    			String propFileName2 = "\\"+configRoot+"\\JAVA_CONF\\"+instance+"\\config.properties";
    			
    			File configFile=new File(configRoot);
    	        if (!configFile.exists()) {
    	           String message="B_CONFIG_ROOT is set to "+configRoot+" but file does not exist";
    	           if (log.isErrorEnabled()) {
    	              log.error(mName+" "+message);
    	           }
    	           throw new Exception(message);
    	        }
     
    			inputStream = new FileInputStream(propFileName2);
     
    			if (inputStream != null) {
    				prop.load(inputStream);
    			} else {
    				throw new FileNotFoundException("property file '" + propFileName2 + "' not found ");
    			}
     
    			Date time = new Date(System.currentTimeMillis());
     
// Get the property value and print it out
    			//String node = prop.getProperty("node");
    			//log.info("node="+node);
    			

// Iterate over all props 
    			if (log.isDebugEnabled()) {
    			   for (Map.Entry < Object, Object> i: prop.entrySet()) {
    			      log.debug("props="+i.getKey() + " " + i.getValue());
    			   }
    			}
     
    		
    		} catch (Exception e) {
    			System.out.println("Exception: " + e);
    		} finally {
    			inputStream.close();
    		}
            return prop;
    	}
    
}