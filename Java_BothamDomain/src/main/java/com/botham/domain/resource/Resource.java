package com.botham.domain.resource;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;


@Entity 
@Table(name = "resource")
public class Resource implements Serializable {

	private static final long serialVersionUID = -8903512476538911385L;

	public Resource() {
	}
	

/*
	@OneToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "Resource_Asset", 
	                  joinColumns = { @JoinColumn(name = "id") }, 
	                  inverseJoinColumns = { @JoinColumn(name = "a_system_name") })
	
	Set<ResourceAsset> resourceAssets = new HashSet<>();

	
	
	public Set<ResourceAsset> getResourceAssets() {
		return resourceAssets;
	}

	public void setResourceAssets(Set<ResourceAsset> resourceAssets) {
		this.resourceAssets = resourceAssets;
	}
	*/



	
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((checkinCount == null) ? 0 : checkinCount.hashCode());
		result = prime * result + ((checkinServer == null) ? 0 : checkinServer.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + ((lastCheckin == null) ? 0 : lastCheckin.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((timeAtRemote == null) ? 0 : timeAtRemote.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resource other = (Resource) obj;
		if (checkinCount == null) {
			if (other.checkinCount != null)
				return false;
		} else if (!checkinCount.equals(other.checkinCount))
			return false;
		if (checkinServer == null) {
			if (other.checkinServer != null)
				return false;
		} else if (!checkinServer.equals(other.checkinServer))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (lastCheckin == null) {
			if (other.lastCheckin != null)
				return false;
		} else if (!lastCheckin.equals(other.lastCheckin))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;

		if (timeAtRemote == null) {
			if (other.timeAtRemote != null)
				return false;
		} else if (!timeAtRemote.equals(other.timeAtRemote))
			return false;
		return true;
	}


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

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}


	@Column(name = "time_at_remote")
	private Timestamp timeAtRemote;
	
	
	@Column(name = "client_version", length = 20)
	private String clientVersion;
	
	
}
