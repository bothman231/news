package com.botham.news.domain.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "base")
public class Db implements Serializable {

	public Db(String key, String desc) {
		super();
		this.key = key;
		this.desc = desc;
	}

	private static final long serialVersionUID = -2036029055149693802L;

	public Db() {

	}

	@Id
	@Column(name = "keyvalue", length = 10)
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "description", length = 50)
	private String desc;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
