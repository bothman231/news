package com.botham.base;

public class Storage {
   public Storage() {
	   
   }
   
private String path;
private String name;
private String desc;
private long totalSpace;
public long getTotalSpace() {
	return totalSpace;
}
public void setTotalSpace(long totalSpace) {
	this.totalSpace = totalSpace;
}

private long usableSpace;

public long getUsableSpace() {
	return usableSpace;
}
public void setUsableSpace(long usableSpace) {
	this.usableSpace = usableSpace;
}
public String getPath() {
	return path;
}
public Storage(String path, String name, String desc, long totalSpace, long usableSpace) {
	super();
	this.path = path;
	this.name = name;
	this.desc = desc;
	this.totalSpace=totalSpace;
	this.usableSpace=usableSpace;
}
public void setPath(String path) {
	this.path = path;
}
public String getName() {
	return name;
}
@Override
public String toString() {
	return "Storage [path=" + path + ", name=" + name + ", desc=" + desc +
		   ", totalSpace="+totalSpace+ 
		   ", usableSpace="+usableSpace+"]";
}
public void setName(String name) {
	this.name = name;
}
public String getDesc() {
	return desc;
}
public void setDesc(String desc) {
	this.desc = desc;
}

   
}
