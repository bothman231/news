package com.botham.news.domain.jobs;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.botham.base.GlobalConstants;




public class JobsMirror implements Serializable {

	private static final long serialVersionUID = 8781208166016497520L;
	
	public static final String SCHEDULE_DAILY="D";
	public static final String STATUS_ACTIVE="A";
	public static final String CURRENT_STATUS_IN_PROGRESS="I";
	public static final String CURRENT_STATUS_COMPLETE="C";
	public static final String STATUS_ERROR="E";
	
	public JobsMirror() {
// Constructor
		name="";
		
	}
	

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	private String description;
	

	private String status;
	

	private String currentStatus;
	
	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public java.sql.Timestamp getStart() {
		return start;
	}

	public void setStart(java.sql.Timestamp start) {
		this.start = start;
	}

	public java.sql.Timestamp getEnd() {
		return end;
	}

	public void setEnd(java.sql.Timestamp end) {
		this.end = end;
	}


	private String info;
	

	private java.sql.Timestamp start;
	

	private java.sql.Timestamp end;
	

	private java.sql.Timestamp lastRun;
	
	public java.sql.Timestamp getLastRun() {
		return lastRun;
	}

	public void setLastRun(java.sql.Timestamp lastRun) {
		this.lastRun = lastRun;
	}
	
	

	private String schedule;

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	

	private int id;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	private int notificationsSent;

	public int getNotificationsSent() {
		return notificationsSent;
	}

	public void setNotificationsSent(int notificationsSent) {
		this.notificationsSent = notificationsSent;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("name=" + name + GlobalConstants.FIELD_DELIM
				+ GlobalConstants.ROW_DELIM);
		sb.append("description=" + description + GlobalConstants.FIELD_DELIM
				+ GlobalConstants.ROW_DELIM);
		sb.append("status=" + status + GlobalConstants.FIELD_DELIM
				+ GlobalConstants.ROW_DELIM);		
		sb.append("activeStart=" + activeStart + GlobalConstants.FIELD_DELIM
				+ GlobalConstants.ROW_DELIM);	
		sb.append("activeEnd=" + activeEnd + GlobalConstants.FIELD_DELIM
				+ GlobalConstants.ROW_DELIM);	
		sb.append("currentStatus=" + currentStatus + GlobalConstants.FIELD_DELIM
				+ GlobalConstants.ROW_DELIM);		
		sb.append("start=" + start + GlobalConstants.FIELD_DELIM
				+ GlobalConstants.ROW_DELIM);		
		sb.append("end=" + end + GlobalConstants.FIELD_DELIM
				+ GlobalConstants.ROW_DELIM);			
		sb.append("info=" + info + GlobalConstants.FIELD_DELIM
				+ GlobalConstants.ROW_DELIM);				
		sb.append("allowConcurrent=" + allowConcurrent + GlobalConstants.FIELD_DELIM
				+ GlobalConstants.ROW_DELIM);			
		sb.append("lastRun=" + lastRun + GlobalConstants.FIELD_DELIM
				+ GlobalConstants.ROW_DELIM);		
		sb.append("schedule=" + schedule + GlobalConstants.FIELD_DELIM
				+ GlobalConstants.ROW_DELIM);		
		sb.append("runOnNodes=" + runOnNodes + GlobalConstants.FIELD_DELIM
				+ GlobalConstants.ROW_DELIM);		
		sb.append("runOnInstances=" + runOnInstances + GlobalConstants.FIELD_DELIM
				+ GlobalConstants.ROW_DELIM);	
		sb.append("statusAcknowledged=" + statusAcknowledged + GlobalConstants.FIELD_DELIM
				+ GlobalConstants.ROW_DELIM);	
		sb.append("id=" + id + GlobalConstants.FIELD_DELIM
				+ GlobalConstants.ROW_DELIM);	
		return sb.toString();
	}
	

	private String activeStart;
	


	public String getActiveStart() {
		return activeStart;
	}

	public void setActiveStart(String activeStart) {
		this.activeStart = activeStart;
	}


	private String activeEnd;

	public String getActiveEnd() {
		return activeEnd;
	}

	public void setActiveEnd(String activeEnd) {
		this.activeEnd = activeEnd;
	}
	

	private String allowConcurrent;

	public String getAllowConcurrent() {
		return allowConcurrent;
	}

	public void setAllowConcurrent(String allowConcurrent) {
		this.allowConcurrent = allowConcurrent;
	}
	
	
	public Jobs clone(Jobs entity) {
	   Jobs clone = new Jobs();
	   clone.setName(entity.getName());
	   clone.setStatus(entity.getStatus());
	   clone.setDescription(entity.getDescription());
	   clone.setRunOnNodes(entity.getRunOnNodes());
	   clone.setRunOnInstances(entity.getRunOnInstances());
	   clone.setStatusAcknowledged(entity.getStatusAcknowledged());
	   return clone;
	}
	
	public Jobs trim(Jobs entity) {

		if (entity.getName() != null) {
			entity.setName(entity.getName().trim());
		}

		return entity;
	}
	
	
	
	

	private String runOnNodes;

	public String getRunOnNodes() {
		return runOnNodes;
	}

	public void setRunOnNodes(String runOnNodes) {
		this.runOnNodes = runOnNodes;
	}
	
	

	private String runOnInstances;

	public String getRunOnInstances() {
		return runOnInstances;
	}

	public void setRunOnInstances(String runOnInstances) {
		this.runOnInstances = runOnInstances;
	}
	
	

	private String statusAcknowledged;
	
	public String getStatusAcknowledged() {
		return statusAcknowledged;
	}

	public void setStatusAcknowledged(String statusAcknowledged) {
		this.statusAcknowledged = statusAcknowledged;
	}
	
	
	
}
