package com.botham.domain.resource;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity 
@Table(name = "resource")
public class Resource implements Serializable {

	private static final long serialVersionUID = -8903512476538911385L;

	public Resource() {
	}
	
	@Id
	@Column(name = "id", length = 10)
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getLastCheckin() {
		return lastCheckin;
	}

	public void setLastCheckin(Timestamp lastCheckin) {
		this.lastCheckin = lastCheckin;
	}

	@Column(name = "name", length = 35)
	private String name;
	
	@Column(name = "last_checkin")
	private Timestamp lastCheckin;
	
	private BigDecimal checkinCount;

	public BigDecimal getCheckinCount() {
		return checkinCount;
	}

	public void setCheckinCount(BigDecimal checkinCount) {
		this.checkinCount = checkinCount;
	}
	
	private String info;

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	@Column(name = "checkin_server", length = 10)
	private String checkinServer;

	public String getCheckinServer() {
		return checkinServer;
	}

	public void setCheckinServer(String checkinServer) {
		this.checkinServer = checkinServer;
	}

	public Timestamp getTimeAtRemote() {
		return timeAtRemote;
	}

	public void setTimeAtRemote(Timestamp timeAtRemote) {
		this.timeAtRemote = timeAtRemote;
	}

	@Column(name = "time_at_remote")
	private Timestamp timeAtRemote;
	
	
}
