package com.botham;

import java.util.ArrayList;
import java.util.List;

public class Info {
   public Info() {
	   
   }
   
@Override
public String toString() {
	return "Info [systemName=" + systemName + ", instance=" + instance + ", configRoot=" + configRoot + "]";
}

public Info(String systemName, String instance, String configRoot) {
	super();
	this.systemName = systemName;
	this.instance = instance;
	this.configRoot = configRoot;
	this.storageList=new ArrayList<Storage>();
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

   
}
