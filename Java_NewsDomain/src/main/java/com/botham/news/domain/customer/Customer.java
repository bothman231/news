package com.botham.news.domain.customer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity 
@Table(name = "customer")
public class Customer implements Serializable {


	private static final long serialVersionUID = -2036029055149693802L;

	public Customer() {

	}
	
	@Id
	@Column(name = "customer_id", length = 10)
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "customer_name", length = 35)
	private String name;

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public Customer(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
