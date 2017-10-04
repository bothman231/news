package com.botham.news.rest.Java_NewsRestApp1;

import java.util.List;

public class RestEntity {

	public RestEntity() {

	}

	public RestEntity(String message) {
		this.message = message;

	}

	private String message;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public <T> List<T> getObjects() {
		return (List<T>) objects;
	}

	public <T> void setObjects(List<T> objects) {
		this.objects = (List<Object>) objects;
	}

	private List<Object> objects;

}
