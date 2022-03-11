package com.pizzahouse.service.model;

import java.util.List;

public class OrderDetail {
	
	private String pizzaTypeUuid;
	
	private String pizzaSizeUuid;
	
	private List<String> pizzaToppingUuidList;
	
	private int quantity;

	public String getPizzaTypeUuid() {
		return pizzaTypeUuid;
	}

	public void setPizzaTypeUuid(String pizzaTypeUuid) {
		this.pizzaTypeUuid = pizzaTypeUuid;
	}

	public String getPizzaSizeUuid() {
		return pizzaSizeUuid;
	}

	public void setPizzaSizeUuid(String pizzaSizeUuid) {
		this.pizzaSizeUuid = pizzaSizeUuid;
	}

	public List<String> getPizzaToppingUuidList() {
		return pizzaToppingUuidList;
	}

	public void setPizzaToppingUuidList(List<String> pizzaToppingUuidList) {
		this.pizzaToppingUuidList = pizzaToppingUuidList;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	
}
