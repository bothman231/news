package com.botham.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.swing.filechooser.FileSystemView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseHelper {
	
	public static final boolean defaultAllRequired=true;
	
	public static final String systemNameRef="B_SYSTEM_NAME";
	public static final String configRootRef="B_CONFIG_ROOT";
	public static final String instanceRef="instance";
	public static final String nodeRef="node";
	public static final String checkInUrlRef="checkInUrl";
	public static final String javaConfRef="JAVA_CONF";
	public static final String configPropertiesRef="config.properties";
	
	public static final String configRootDefault="c:"+System.getProperty("file.separator")+"unique";
	
	public static final String instanceDefault="A";
	public static final String checkInUrlDefault="http://hudzen10:5900/api/checkin";
	public static final String nodeDefault="01";
	//public static final String driveDefault="c:";
	
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
	
	@SuppressWarnings("unused")
	public static Properties getPropValues(String configRoot, String instance) throws IOException, Exception {
		
		String mName="BaseHelper:getPropValues";
		
		if (log.isDebugEnabled()) {
			log.debug(mName+" Starts, loading properties from, configRoot="+configRoot+" instance="+instance);
		}
		
		Properties prop = new Properties();
		
		InputStream inputStream=null;
		
		try {

			String propFileName2 = configRoot+
					               System.getProperty("file.separator")+
					               BaseHelper.javaConfRef+
					               System.getProperty("file.separator")+
					               instance+
					               System.getProperty("file.separator")+
					               BaseHelper.configPropertiesRef;
			
			if (log.isDebugEnabled()) {
			   log.debug(mName+" propFile="+propFileName2);
			}
			
			File configFile=new File(propFileName2);
			
	        if (!configFile.exists()) {
	           String message="property file does not exist @ "+propFileName2;
	        }
	        
			inputStream = new FileInputStream(propFileName2);
			
			if (inputStream == null) {
		       String message="input stream is null";
			}
	        
            prop.load(inputStream);
				  
//Iterate over all props 
			boolean showAllProps=false;
			//boolean showAllProps=true;
			if (showAllProps) {
			   if (log.isDebugEnabled()) {
			
			      for (Map.Entry < Object, Object> i: prop.entrySet()) {
			        log.debug(mName+" props="+i.getKey() + " " + i.getValue());
			      }
			   }
			}
 
		
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
        return prop;
	}
	
	
	public static Properties defaultProps() {
	   Properties prop = new Properties();
 	   prop.setProperty(nodeRef, nodeDefault);
 	   prop.setProperty(checkInUrlRef, checkInUrlDefault);		
	   return prop;
	}
	
	public static List<Storage> getRoots() {


		String mName = "getRoots";
		FileSystemView fileSystemView = FileSystemView.getFileSystemView();
		/*
		 * File[] roots = fileSystemView.getRoots(); for (int i=0; i<roots.length; i++)
		 * { log.info(mName+" "+roots[i]); }
		 */
		
		List<Storage> storageList=new ArrayList<>();
		
		File[] f = File.listRoots();
		
		for (int i = 0; i < f.length; i++) {
			if (log.isDebugEnabled()) {
				log.debug(mName + " " + f[i] + " " + fileSystemView.getSystemDisplayName(f[i]) + " " + f[i].getPath() + " "
			        			+ f[i].getName() + " " + toGb(f[i].getTotalSpace()) + " " + toGb(f[i].getUsableSpace()));
			}
			Storage storage = new Storage(f[i].getPath(), f[i].getName(), fileSystemView.getSystemDisplayName(f[i]) 
					                                    ,f[i].getTotalSpace(), f[i].getUsableSpace());
			storageList.add(storage);
		}

		if (log.isDebugEnabled()) {
			log.debug(mName+" returning "+storageList.size());
		}
		return storageList;

	}
	
	public static long toGb(long value) {
		return value / 1024 / 1024 / 1024;
	}
	
	
	public static String getIp() {
		String mName = "getIp";

		BufferedReader in = null;
		URL whatismyip = null;

		try {
			whatismyip = new URL("http://checkip.amazonaws.com");

			in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			String ip = in.readLine();
			return ip;

		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(mName + " Could not get IP from WS "+whatismyip.toString());
			}
			e.printStackTrace();
			return "";

		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
// This is a public server that takes an IP address an returns its geo info
// Including ISP info.
	
	public static XLocation ipToGeo(String ip) {
		String mName="ipToGeo";
		RestTemplate restTemplate = new RestTemplate();

		//String ip = "82.16.111.250";
		//ip = "";
		//ip = IpChecker.getIp();

		String fooResourceUrl = "https://ipinfo.io/" + ip + "/json";

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Accept", "application/json");
		ResponseEntity<String> response=null;
		
		try {
			response = restTemplate.getForEntity(fooResourceUrl + "", String.class);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(mName+" Caught Exception here "+e.getMessage()+" "+e.getClass());
			}
			return new XLocation(ip, "COULD NOT DETERMIN LOCATION");
		}

		if (log.isDebugEnabled()) {
		   log.debug(mName+" "+response.toString());
		}

		ObjectMapper mapper = new ObjectMapper();
		JsonNode root;
		try {
			root = mapper.readTree(response.getBody());
			JsonNode region = root.path("region");
			if (log.isDebugEnabled()) {
			   log.debug(mName+" Region=" + region);
			}

		} catch (JsonProcessingException e) {
			if (log.isErrorEnabled()) {
			   log.error(mName+" JsonPorcessingException");
			}
			return new XLocation(ip, "COULD NOT DETERMIN LOCATION");
			//e.printStackTrace();
			
		} catch (IOException e) {
			if (log.isErrorEnabled()) {
				log.error(mName+" IOException");
			}
			return new XLocation(ip, "COULD NOT DETERMIN LOCATION");
			//e.printStackTrace();
		
		} catch (ResourceAccessException e) {
			if (log.isErrorEnabled()) {
				log.error(mName+" ResourceAccessException");
			}
			return new XLocation(ip, "COULD NOT DETERMIN LOCATION");
			//e.printStackTrace();
		}
		
		

		// assertThat(response.getStatusCode(), equals(HttpStatus.OK));

		XLocation location = restTemplate.getForObject(fooResourceUrl + "", XLocation.class);

		if (log.isDebugEnabled()) {
		   log.debug(mName+" "+location.toString());
		}
		
		return location;

	}
	
	public static String getSystemName() {
		String mName="BaseHelper:getSystemName";
		
		if (log.isDebugEnabled()) {
			log.debug(mName+" Starts");
		}
		
		String systemName="";
		
// These are env vars set at the OP SYS Level  
// Must be set by whoever sets up the machine
		
		if (System.getenv(systemNameRef) !=null && !System.getenv(systemNameRef).isEmpty()) {
             systemName=System.getenv(systemNameRef);
             if (log.isDebugEnabled()) {
                log.debug(mName+" "+systemNameRef+"="+systemName+"*");
             }
             return systemName;
		} else {
			if (log.isDebugEnabled()) {
			   log.debug(mName+" "+systemNameRef+" is not present");
			}			
		}
		
		
		java.net.InetAddress localMachine;
		
		try {
			localMachine = java.net.InetAddress.getLocalHost();
			systemName=localMachine.getHostName();
			return systemName;
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
			
		}
		
		if (log.isDebugEnabled()) {
			log.debug(mName+" Ends");
		}
		return "unknown";
	}
	
	
	public static String getConfigRoot() throws Exception {
		String mName="BaseHelper:getConfigRoot";
		
		if (log.isDebugEnabled()) {
			log.debug(mName+" Starts");
		}
		
        String configRoot=System.getenv(configRootRef);      
        
        if (log.isDebugEnabled()) {
        	log.debug(mName+" "+configRootRef+"="+configRoot+"*");
        }
    
        if (configRoot==null || configRoot.isEmpty()) {
  		   String errorMessage=configRootRef+" is not set ";
    	   if (log.isErrorEnabled()) {
    	      log.error(mName+" "+errorMessage);
    	   }
    	   
    	   if (defaultAllRequired) {
    	      configRoot=configRootDefault;
    	      if (log.isDebugEnabled()) {
    	         log.debug(mName+" defaulted to "+configRoot+"*");
    	      }
    	      return configRoot;
    	   } else {
    		  throw new Exception(errorMessage);
    	   }
    		  
        }  else {
    	   if (log.isDebugEnabled()) {
    		  log.debug(mName+" "+configRootRef+"="+configRoot);
    	   }
    	   return configRoot;
    	}
    }
	
	
	public static String getInstance() throws Exception {
		String mName="BaseHelper:getInstance";
		
		if (log.isDebugEnabled()) {
			log.debug(mName+" Starts");
		}
		
 
        String instance=System.getProperty(instanceRef);
        
        if (log.isDebugEnabled()) {
        	log.debug(mName+" "+instance+"="+instance+"*");
        }
    
        if (instance==null || instance.isEmpty()) {
  		   String errorMessage=instanceRef+" is not set ";
    	   if (log.isErrorEnabled()) {
    	      log.error(mName+" "+errorMessage);
    	   }
    	   
    	   if (defaultAllRequired) {
    	      instance=instanceDefault;
    	      if (log.isDebugEnabled()) {
    	         log.debug(mName+" defaulted to "+instance+"*");
    	      }
    	      return instance;
    	   } else {
    		  throw new Exception(errorMessage);
    	   }
    		  
        }  else {
    	   if (log.isDebugEnabled()) {
    		  log.debug(mName+" "+instanceRef+"="+instance);
    	   }
    	   return instance;
    	}
    }
	
	
}
