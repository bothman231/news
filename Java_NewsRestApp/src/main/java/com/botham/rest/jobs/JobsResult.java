package com.botham.rest.jobs;

public class JobsResult {

	public JobsResult() {

	}
	
	private String resultMessage = "";
	private String output = "";
	
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
