package com.botham.domain.resource;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;



@Embeddable
public class ResourceAssetPK implements Serializable {

	private static final long serialVersionUID = -8903512476538911385L;

	public ResourceAssetPK(String systemName, String assetName) {
		this.systemName=systemName;
		this.assetName=assetName;
    }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assetName == null) ? 0 : assetName.hashCode());
		result = prime * result + ((systemName == null) ? 0 : systemName.hashCode());
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
		ResourceAssetPK other = (ResourceAssetPK) obj;
		if (assetName == null) {
			if (other.assetName != null)
				return false;
		} else if (!assetName.equals(other.assetName))
			return false;

		if (systemName == null) {
			if (other.systemName != null)
				return false;
		} else if (!systemName.equals(other.systemName))
			return false;
		return true;
	}

	public ResourceAssetPK() {
	}
	
	@Column(name = "a_system_name", length = 10)
	private String systemName;
	
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}


	@Column(name = "a_asset_name", length = 20)
	private String assetName;

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	
	
	/*
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "a_system_name", referencedColumnName = "id", insertable = false, updatable = false)})
	private Resource resource;

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
*/
	
	
	
}
