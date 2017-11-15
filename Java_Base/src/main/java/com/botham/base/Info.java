package com.botham.base;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



public class Info {
   public Info() {
	   
   }
   
@Override
public String toString() {
	StringBuilder sb = new StringBuilder();
	
	sb.append("Info [systemName=" + systemName + ", instance=" + instance + ", configRoot=" + configRoot + 
			      ", javaVersion="+javaVersion+", build="+build+
			      ", localTime="+localTime+
			      
			      "]");
	
	if (geoLocation==null) {
		sb.append(" geoLocation is null");
	} else {
	  sb.append(" "+geoLocation.toString());		
	}
	
	if (storageList==null) {
		sb.append(" storageList is null");
	} else {
      int x=0;
	  for (Storage s : storageList) {
		  x++;
		  sb.append(s.toString());		
	  }
	}
	
	return sb.toString();
	
}

public Info(String systemName, String instance, String configRoot, 
		    String javaVersion, String build, Timestamp localTime) {
	super();
	this.systemName = systemName;
	this.instance = instance;
	this.configRoot = configRoot;
	
	this.storageList=new ArrayList<Storage>();
	
	this.javaVersion=javaVersion;
	this.build=build;
	this.localTime=localTime;
	this.geoLocation=new XLocation();

	
}

private String systemName;
public String getSystemName() {
	return systemName;
}
public void setSystemName(String systemName) {
	this.systemName = systemName;
}
public String getInstance() {
	return instance;
}
public void setInstance(String instance) {
	this.instance = instance;
}
public String getConfigRoot() {
	return configRoot;
}
public void setConfigRoot(String configRoot) {
	this.configRoot = configRoot;
}

private String instance;
private String configRoot;
private List<Storage> storageList;

public List<Storage> getStorageList() {
	return storageList;
}

public void setStorageList(List<Storage> storageList) {
	this.storageList = storageList;
} 

private String javaVersion;
public String getJavaVersion() {
	return javaVersion;
}

public void setJavaVersion(String javaVersion) {
	this.javaVersion = javaVersion;
}

private String build;
public String getBuild() {
	return build;
}

public void setBuild(String build) {
	this.build = build;
}


public Timestamp getLocalTime() {
	return localTime;
}

public void setLocalTime(Timestamp localTime) {
	this.localTime = localTime;
}

public XLocation getGeoLocation() {
	return geoLocation;
}

public void setGeoLocation(XLocation geoLocation) {
	this.geoLocation = geoLocation;
}

private Timestamp localTime;

   


private XLocation geoLocation;

}

