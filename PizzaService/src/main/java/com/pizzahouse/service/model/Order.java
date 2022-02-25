package com.pizzahouse.service.model;

import java.util.List;

import com.pizzahouse.service.model.OrderDetail;

public class Order {

	private int userId;
	private int session;
	private List<OrderDetail> details;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getSession() {
		return session;
	}
	public void setSession(int session) {
		this.session = session;
	}

	public List<OrderDetail> getDetails() {
		return details;
	}
	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}
	
	
	
}
