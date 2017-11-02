package com.botham;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.swing.filechooser.FileSystemView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.botham.domain.resource.Info;
import com.botham.domain.resource.Storage;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        
		
//Gather all system info.
		// 1 - Load the info.json from the CONFIG_ROOT

		Info info = new Info("fncserver3", "A", "c:\\unique");
		
		//infoJsonToPojo();
		infoPojoToJson(info);
		
		info=getRoots(systemName, instance, configRoot);
		infoPojoToJson(info);

		checkIn(info);
        log.info(mName+" The time is now {}", dateFormat.format(new Date())+" "+systemName+" instance="+instance+" node="+node);
        
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
    	
	// Use reflection to get an Annotation and one of its values
	public void getAnValue() {
		boolean debug=false;
		
		String mName = "getAnValue";
		log.info(mName + " Starts");
		// This class
		Method[] methods = ScheduledTasks.class.getMethods();
		// All of its methods
		for (Method m : methods) {
			//log.info(mName + " " + m.getName());

			if (m.isAnnotationPresent(Scheduled.class)) {
				//log.info(mName + " present " + m.getAnnotation(Scheduled.class).toString());
				Scheduled ta = m.getAnnotation(Scheduled.class);
				//log.info(mName + " fixedRate set to " + ta.fixedRate());
				//System.out.println(ta.toString());
			}
		}
	}
    	
    	
    	
    	
        @Scheduled(fixedRateString = "${schedTime}" )  // 20 seconds"
        public void anotherTime() throws Exception {
            log.info("The time is now {}", dateFormat.format(new Date()));
            //anotherTime.
        }
        
        
        public static void infoJsonToPojo() throws JsonParseException, JsonMappingException, IOException {			
			ObjectMapper mapper = new ObjectMapper();
			Info info = mapper.readValue(new File("c:\\tmp\\info.json"), Info.class);
        }
        
        public static void infoPojoToJson(Info info) throws JsonParseException, JsonMappingException, IOException {
        	String mName="infoPojoToJson";
        	ObjectMapper mapper = new ObjectMapper();
        	try {
        	   String json = mapper.writeValueAsString(info);
        	   mapper.writeValue(new File("c:\\tmp\\info.json"), info);
        	   log.info(mName+" "+json);
        	} catch (Exception e) {
        	   log.error(e.getMessage());
        	}
        	
        }
    	
    	@PostConstruct
    	public void postContruct() {
    		getAnValue();
    	}
    	
    	public static Info getRoots(String systemName, String instance, String configRoot) {
    		
    		Info info = new Info(systemName, instance, configRoot);
    		
    		String mName="getRoots";
    		FileSystemView fileSystemView = FileSystemView.getFileSystemView();
    		/*
    		File[] roots = fileSystemView.getRoots();
    		for (int i=0; i<roots.length; i++) {
    			log.info(mName+" "+roots[i]);
    		}
    		*/
    		File[] f = File.listRoots();
    		for (int i=0; i<f.length; i++) {
    			log.info(mName+" "+f[i]+" "+fileSystemView.getSystemDisplayName(f[i])+
    					                   " "+f[i].getPath()+" "+f[i].getName()+
    					                " "+toGb(f[i].getTotalSpace())+
    					                " "+toGb(f[i].getUsableSpace()));
    			Storage s = new Storage(f[i].getPath(), f[i].getName(), fileSystemView.getSystemDisplayName(f[i]));
    			info.getStorageList().add(s);
    		}
    		
            return info;
    		
    	}
    	
    	public static long toGb(long value) {
    		return value / 1024 / 1024 / 1024;
    	}
    	
    	
    	public void checkIn(Info info) { 
    		String mName="CheckIn";
    		
    	   String checkInUrl="http://localhost:8073/api/checkin";
    	   
    	   RestTemplate restTemplate = new RestTemplate();

    	   try {
    		   
    	      HttpEntity<Info> request = new HttpEntity<>(info);
    		  ResponseEntity<Info> response = restTemplate.exchange(checkInUrl, HttpMethod.POST, request, Info.class);
              //String s = restTemplate.getForObject(checkInUrl, String.class);
    		  
    		  
              
              log.info(mName+" reponse="+response.getBody()+" "+response.getStatusCode());
              
    	   } catch (Exception e) {
    		  log.error(mName+" "+e.getMessage());
    	   }
           
           
    	    
    	   
    	}
    	

    	
}