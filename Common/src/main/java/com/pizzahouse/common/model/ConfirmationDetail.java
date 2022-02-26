package com.pizzahouse.common.model;

import java.util.List;

public class ConfirmationDetail {
	
	private int pizzaTypeId;
	private int subItemReferenceId;
	private int subItemCategoryId;
	private int quantity;
	private float singlePrice;
	private float subTotal;
	public int getPizzaTypeId() {
		return pizzaTypeId;
	}
	public void setPizzaTypeId(int pizzaTypeId) {
		this.pizzaTypeId = pizzaTypeId;
	}
	public int getSubItemReferenceId() {
		return subItemReferenceId;
	}
	public void setSubItemReferenceId(int subItemReferenceId) {
		this.subItemReferenceId = subItemReferenceId;
	}
	public int getSubItemCategoryId() {
		return subItemCategoryId;
	}
	public void setSubItemCategoryId(int subItemCategoryId) {
		this.subItemCategoryId = subItemCategoryId;
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
