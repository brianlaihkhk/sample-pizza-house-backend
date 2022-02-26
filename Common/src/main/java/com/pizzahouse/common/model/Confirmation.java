package com.pizzahouse.common.model;

import java.util.List;

public class Confirmation {

	private int userId;
	private float totalAmount;
	private List<ConfirmationDetail> details;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
