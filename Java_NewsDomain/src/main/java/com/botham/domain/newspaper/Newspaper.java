package com.botham.domain.newspaper;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity 
@Data
@Table(name = "newspaper")
public class Newspaper implements Serializable {


	private static final long serialVersionUID = -2036029055149693802L;

	public Newspaper() {

	}
	
	@Id
	@Column(name = "news_number", length = 10)
	private String newsNumber;
	
	@Column(name = "news_desc", length = 35)
	private String newsDesc;
	
	@Column(name = "news_id", length = 30)
	private String newsId;
	
}
