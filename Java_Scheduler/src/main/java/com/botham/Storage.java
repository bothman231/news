package com.botham;

public class Storage {
   public Storage() {
	   
   }
   
private String path;
private String name;
private String desc;
public String getPath() {
	return path;
}
public Storage(String path, String name, String desc) {
	super();
	this.path = path;
	this.name = name;
	this.desc = desc;
}
public void setPath(String path) {
	this.path = path;
}
public String getName() {
	return name;
}
@Override
public String toString() {
	return "Storage [path=" + path + ", name=" + name + ", desc=" + desc + "]";
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
