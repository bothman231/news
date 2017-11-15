package com.botham.domain.resource;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;


@Entity 
@Table(name = "resource_asset")
public class ResourceAsset implements Serializable {

	private static final long serialVersionUID = -8903512476538911385L;

	public ResourceAsset(String systemName, String name) {
		
	}
	
	public ResourceAsset(ResourceAssetPK resourceAssetPK) {
		this.resourceAssetPK=resourceAssetPK;
		
	}
		
	@EmbeddedId
	private ResourceAssetPK resourceAssetPK;

	public ResourceAssetPK getResourceAssetPK() {
		return resourceAssetPK;
	}

	public void setResourceAssetPK(ResourceAssetPK resourceAssetPK) {
		this.resourceAssetPK = resourceAssetPK;
	}
	
	
//	@OneToMany(mappedBy = "resource")
//	private Set<Resource> resources = new HashSet<>();
}
