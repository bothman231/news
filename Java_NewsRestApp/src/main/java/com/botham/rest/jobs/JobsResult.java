package com.botham.rest.jobs;

import java.util.ArrayList;
import java.util.List;

public class JobsResult {

	public JobsResult() {

	}
	
	private String resultMessage = "";
	private String output = "";
	private List<Object> list = new ArrayList<Object>();
	
	
	public List<Object> getList() {
		return list;
	}



	public void setList(List<Object> list) {
		this.list = list;
	}



	public String getOutput() {
		return output;
	}



	public void setOutput(String output) {
		this.output = output;
	}



	public JobsResult(String resultMessage) {
       this.resultMessage=resultMessage;
	}



	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

}
