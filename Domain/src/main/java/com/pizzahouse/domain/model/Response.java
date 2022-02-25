package com.pizzahouse.domain.model;

import java.util.List;

public class Response<T> {
	
	private boolean success;
	private ErrorDetail error;
	private List<T> payload;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public ErrorDetail getError() {
		return error;
	}
	public void setError(ErrorDetail error) {
		this.error = error;
	}
	public List<T> getPayload() {
		return payload;
	}
	public void setPayload(List<T> payload) {
		this.payload = payload;
	}
	
	
}
