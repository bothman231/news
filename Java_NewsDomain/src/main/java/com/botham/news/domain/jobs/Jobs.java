package com.botham.news.domain.jobs;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.botham.base.GlobalConstants;



@Entity 
@Table(name = "jobs")
public class Jobs implements Serializable {

	private static final long serialVersionUID = 8781208166016497520L;
	
	public static final String SCHEDULE_DAILY="D";
	public static final String STATUS_ACTIVE="A";
	public static final String CURRENT_STATUS_IN_PROGRESS="I";
	public static final String CURRENT_STATUS_COMPLETE="C";
	public static final String STATUS_ERROR="E";
	
	public Jobs() {
// Constructor
		name="";
		
	}
	
	//@Id
	@Column(name = "jobs_name", length = 50)
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

	@Column(name = "jobs_description", length = 100)
	private String description;
	
	@Column(name = "jobs_status", length = 1)
	private String status;
	
	@Column(name = "jobs_current_status", length = 1)
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

	@Column(name = "jobs_info", length = 100)
	private String info;
	
	@Column(name = "jobs_start")
	private java.sql.Timestamp start;
	
	@Column(name = "jobs_end")
	private java.sql.Timestamp end;
	
	@Column(name = "jobs_last_run")
	private java.sql.Timestamp lastRun;
	
	public java.sql.Timestamp getLastRun() {
		return lastRun;
	}

	public void setLastRun(java.sql.Timestamp lastRun) {
		this.lastRun = lastRun;
	}
	
	
	@Column(name = "jobs_schedule", length = 1)
	private String schedule;

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	
	@Id
	@Column(name = "jobs_id")
	private int id;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "jobs_notifications_sent")
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
	
	@Column(name = "jobs_active_start")
	private String activeStart;
	


	public String getActiveStart() {
		return activeStart;
	}

	public void setActiveStart(String activeStart) {
		this.activeStart = activeStart;
	}

	@Column(name = "jobs_active_end")
	private String activeEnd;

	public String getActiveEnd() {
		return activeEnd;
	}

	public void setActiveEnd(String activeEnd) {
		this.activeEnd = activeEnd;
	}
	
	@Column(name = "jobs_allow_concurrent", length = 1)
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
	
	
	
	
	@Column(name = "jobs_run_on_nodes", length = 25)
	private String runOnNodes;

	public String getRunOnNodes() {
		return runOnNodes;
	}

	public void setRunOnNodes(String runOnNodes) {
		this.runOnNodes = runOnNodes;
	}
	
	
	@Column(name = "jobs_run_on_instances", length = 25)
	private String runOnInstances;

	public String getRunOnInstances() {
		return runOnInstances;
	}

	public void setRunOnInstances(String runOnInstances) {
		this.runOnInstances = runOnInstances;
	}
	
	
	@Column(name = "jobs_status_acknowledged", length = 1)
	private String statusAcknowledged;
	
	public String getStatusAcknowledged() {
		return statusAcknowledged;
	}

	public void setStatusAcknowledged(String statusAcknowledged) {
		this.statusAcknowledged = statusAcknowledged;
	}
	
	
	
}
