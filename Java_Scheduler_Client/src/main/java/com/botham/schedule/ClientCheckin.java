package com.botham.schedule;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Properties;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.botham.base.BaseHelper;
import com.botham.base.Info;
import com.botham.base.XLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// Very simple spring boot scheduler app,
// Runs every 5000 ms (5 secs).

@Component
@RefreshScope
@Configuration
public class ClientCheckin {

	public static boolean geoObtained = false;
	public static XLocation geoLocation = new XLocation();
	
	private final String cName="Client ";
	
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public String checkInUrl="";
    
    public static String systemName;
    

    public void doIt() throws Exception {
    	
    	
        String mName="doIt";

        String configRoot=System.getenv("B_CONFIG_ROOT");        
        log.debug(mName+" B_CONFIG_ROOT="+configRoot);
        
        if (configRoot==null) {
        	if (log.isErrorEnabled()) {
        	   log.error(mName+" B_CONFIG_ROOT is not set");
        	}
        	return;
        }  else {
        	log.debug(mName+" B_CONFIG_ROOT="+configRoot);
        }
        
        //XLocation geoLoctaion=new XLocation();
        
      	 String serverIp=BaseHelper.getIp();
      	 log.error(mName+" last known public ip="+serverIp);
      	 if (geoObtained) {
      		 log.error(mName+" We already have geo dont re-get");
      	 } else {
      		 log.error(mName+" No geo go get it");
      		 geoLocation=BaseHelper.ipToGeo(serverIp);
      		 geoObtained=true;
      	 }
      	 

// This is a var passed from the JVM java -Dinstance=A
        
// This seems to work on remote Tomcat server but not inside STS?        
        String instance=System.getProperty("instance");
        log.debug(mName+" instance="+instance);
        // String node=System.getProperty("node"); // Comes from config.properties
        String node="";
        
        if (instance!=null) {
        	Properties prop = BaseHelper.getPropValues(configRoot, instance);
            node=prop.getProperty("node");  
            checkInUrl=prop.getProperty("checkInUrl");
            log.info(mName+" checkInUrl="+checkInUrl);
        }
        
		
        
		String javaVersion=System.getProperty("java.version");
		if (log.isInfoEnabled()) {
			log.info(mName+" Java Version="+javaVersion);
		}
		
		String build=BaseHelper.getBuildInfo();
				
//Gather all system info.
		// 1 - Load the info.json from the CONFIG_ROOT

		//Info info = new Info("fncserver3", "A", "c:\\unique");
		Info info = new Info(systemName, instance, configRoot, javaVersion, build, new Timestamp(System.currentTimeMillis()));
		info.setGeoLocation(geoLocation);
		
		//infoJsonToPojo();
		infoPojoToJson(info);
			
		//info=getRoots(systemName, instance, configRoot);
		
		Timestamp currentTime=new Timestamp(System.currentTimeMillis());
		info.setLocalTime(currentTime);
		
		checkIn(info);
			
        log.info(cName+mName+" The time is now {}", dateFormat.format(currentTime)+" "+systemName+" instance="+instance+" node="+node);
        
    }
    


 
    	String result = "";
    	InputStream inputStream;
     
    	
    	

    	
    	
    	
    	

        
        
        public static void infoJsonToPojo() throws JsonParseException, JsonMappingException, IOException {						ObjectMapper mapper = new ObjectMapper();
			Info info = mapper.readValue(new File("c:\\tmp\\info.json"), Info.class);
        }
        
        
        
        public static String infoPojoToJson(Info info) throws JsonParseException, JsonMappingException, IOException {
        	String mName="infoPojoToJson";
        	ObjectMapper mapper = new ObjectMapper();
        	String jsonStr=null;
        	try {
        	   jsonStr = mapper.writeValueAsString(info);
        	   mapper.writeValue(new File("c:\\tmp\\info.json"), info);
        	   
        	   //log.info(mName+" "+jsonStr);
        	} catch (Exception e) {
        	   log.error(e.getMessage());
        	}
        	return jsonStr;
        }
    	
    	@PostConstruct
    	public void postContruct() {
    		String mName="postConstruct";
    		//getAnValue();
    		
// These are env vars set at the OP SYS Level    
    		if (System.getenv("B_SYSTEM_NAME") !=null &&
    		    !System.getenv("B_SYSTEM_NAME").isEmpty()) {
                 systemName=System.getenv("B_SYSTEM_NAME");
                 log.debug(mName+" B_SYSTEM_NAME="+systemName);
    		} else {
    			log.error(mName+" B_SYSTEM_NAME is not present");
    		}
                		
    	}
    	

    	

    	
    	
    	public HttpHeaders addRestHeaders() {
           HttpHeaders headers = new HttpHeaders();
           headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
           headers.setContentType(MediaType.APPLICATION_JSON);
           headers.set("myheader", "myheadervalue");
           return headers;
        }
    	
    	
    	@Bean
    	public RestTemplate restTemplate() {
    	    SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

    	    return new RestTemplate(requestFactory);
    	}
    	
    	// http://www.baeldung.com/rest-template    	
// Calls the Checkin rest API    	
    	
    	public void checkIn(Info info) { 
    		
    	   String mName="checkIn";
    	   
// This needs to come from local config..  as it will be
// Localhost for this machine
// Internal ip for anothe machine on this network 192.168.1.100
// External ip for a remote machine
    	   //String checkInUrl="http://localhost:8073/api/checkin";
    	   
    	   //RestTemplate restTemplate = new RestTemplate();
    	   RestTemplate restTemplate=restTemplate();
    	   
    	   //StringEntity s = new StringEntity(infoPojoToJson(info));

    	   try {
    		   
    		   //info.getStorageList().add(new Storage("a", "b", "c"));
    		   
    		  String jsonStr=infoPojoToJson(info);
    		  
    		  
    		  
    		  //info.setBuild("mybuild1234");
    		  
    		  
    		  //JSONObject j = new JSONObject(info);

    		  HttpEntity<Info> entity = new HttpEntity<Info>(info, addRestHeaders());
    		  
    		  
    		  //List<HttpMessageConverter> messageConverters = new ArrayList<>();
    		  //messageConverters.add(new MappingJackson2HttpMessageConverter());
    		  //restTemplate.setMessageConverters(messageConverters);  
    		  
    		  
    		  //HttpEntity<Info> entity = new HttpEntity<Info>(jsonStr, addRestHeaders());
    		  
    		  log.info("outgoing="+entity.getBody()+"*");
    		  
    	      //HttpEntity<String> request = new HttpEntity<>(jsonStr);
    	      
    	      
    	      log.info(mName+" checkInUrl="+checkInUrl);
    	      
    		  ResponseEntity<Info> response = restTemplate.exchange(checkInUrl, 
    				                                                HttpMethod.POST, 
    				                                                entity, 
    				                                                Info.class);
    		  //ResponseEntity<Info> response = restTemplate.postForObject(checkInUrl, info, Info.class);
              //String s = restTemplate.getForObject(checkInUrl, String.class);
    		  
    		  
              
              log.info(mName+" reponse="+response.getBody()+" "+response.getStatusCode());
              
    	   } catch (Exception e) {
    		  log.error(mName+" error="+e.getMessage());
    		  log.error(mName+" CHECKIN SERVER IS NOT UP AT "+checkInUrl);
    		  //e.printStackTrace();
    		  //System.exit(0);
    		  
    	   }
           
           
    	    
    	   
    	}
    	

    	
}