package com.botham.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.swing.filechooser.FileSystemView;

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
	
	
	
	
	public static Properties getPropValues(String configRoot, String instance) throws IOException, Exception {
		String mName="getPropValues";
		Properties prop = new Properties();
		
		InputStream inputStream=null;
		
		try {

			
			//String propFileName1 = "\\unique\\JAVA_CONF\\A\\config.properties";
			String propFileName2 = configRoot+"\\JAVA_CONF\\"+instance+"\\config.properties";
			log.debug(mName+" propFile="+propFileName2);
			
			File configFile=new File(propFileName2);
			
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
			
			

 
//Get the property value and print it out
			//String node = prop.getProperty("node");
			//log.info("node="+node);
			

//Iterate over all props 
			boolean showAllProps=false;
			if (showAllProps) {
			   if (log.isDebugEnabled()) {
			
			      for (Map.Entry < Object, Object> i: prop.entrySet()) {
			        log.debug("props="+i.getKey() + " " + i.getValue());
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
	
	public static Info getRoots(String systemName, String instance, String configRoot,
            String javaVersion, String build) {

Info info = new Info(systemName, instance, configRoot, javaVersion, build, new Timestamp(System.currentTimeMillis()));

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
}
