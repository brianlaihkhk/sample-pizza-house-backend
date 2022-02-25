package com.pizzahouse.common.model;

import java.util.List;

public class OrderConfirmationDetail {
	
	private String itemName;
	
	private String itemDescription;
	
	private int quantity;
	
	private float singlePrice;
	
	private float subTotal;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getSinglePrice() {
		return singlePrice;
	}

	public void setSinglePrice(float singlePrice) {
		this.singlePrice = singlePrice;
	}

	public float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}
	
	


	
}
