package com.pizzahouse.common.model;

import java.util.List;

public class Confirmation {

	private String userUuid;
	private float totalAmount;
	private List<ConfirmationDetail> details;
	
	public String getUserUuid() {
		return userUuid;
	}
	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public List<ConfirmationDetail> getDetails() {
		return details;
	}
	public void setDetails(List<ConfirmationDetail> details) {
		this.details = details;
	}
	
	
}
