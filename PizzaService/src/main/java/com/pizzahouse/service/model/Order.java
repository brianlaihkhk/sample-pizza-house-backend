package com.pizzahouse.service.model;

import java.util.List;

import com.pizzahouse.service.model.OrderDetail;

public class Order {

	private List<OrderDetail> details;

	public List<OrderDetail> getDetails() {
		return details;
	}
	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}
	
	
	
}
