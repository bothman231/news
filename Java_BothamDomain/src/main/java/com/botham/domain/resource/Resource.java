package com.botham.domain.resource;

import java.io.Serializable;
import java.sql.Date;

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
	
	@Column(name = "name", length = 35)
	private String name;
	
	@Column(name = "last_checkin")
	private Date lastCheckin;
}
