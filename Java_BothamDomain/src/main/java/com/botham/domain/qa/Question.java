package com.botham.domain.qa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "questions")
public class Question implements Serializable {

	private static final long serialVersionUID = -3132999400914971075L;

	public Question() {
	}
	


	
	@Id
	@Column(name = "id")
	private Integer id;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}




	@Column(name = "question")
	private String question;
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}




	@Column(name = "type")
	private String type;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "sub_category")
	private String subCategory;
	
	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}




	@Column(name = "notes")
	private String notes;


	
}
