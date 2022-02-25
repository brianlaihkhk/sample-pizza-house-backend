package com.pizzahouse.common.model;

import java.util.List;

public class OrderConfirmation {

	private int userId;
	private float totalAmount;
	private List<OrderConfirmationDetail> details;
	
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
	public List<OrderConfirmationDetail> getDetails() {
		return details;
	}
	public void setDetails(List<OrderConfirmationDetail> details) {
		this.details = details;
	}
	
	
}
