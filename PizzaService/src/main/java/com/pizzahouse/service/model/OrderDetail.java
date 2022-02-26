package com.pizzahouse.service.model;

import java.util.List;

public class OrderDetail {
	
	private int pizzaTypeId;
	
	private int pizzaSizeId;
	
	private List<Integer> pizzaToppingIdList;
	
	private int quantity;

	public int getPizzaTypeId() {
		return pizzaTypeId;
	}

	public void setPizzaTypeId(int pizzaTypeId) {
		this.pizzaTypeId = pizzaTypeId;
	}

	public int getPizzaSizeId() {
		return pizzaSizeId;
	}

	public void setPizzaSizeId(int pizzaSizeId) {
		this.pizzaSizeId = pizzaSizeId;
	}

	public List<Integer> getPizzaToppingIdList() {
		return pizzaToppingIdList;
	}

	public void setPizzaToppingIdList(List<Integer> pizzaToppingIdList) {
		this.pizzaToppingIdList = pizzaToppingIdList;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	
}
