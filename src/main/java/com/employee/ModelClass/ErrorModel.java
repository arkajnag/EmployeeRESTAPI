package com.employee.ModelClass;

import javax.ws.rs.core.Response.Status;

public class ErrorModel {

	private Status errorCode;
	private String errorMessage;
	public Status getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Status errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public ErrorModel(Status errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public ErrorModel() {
	}
	
}
