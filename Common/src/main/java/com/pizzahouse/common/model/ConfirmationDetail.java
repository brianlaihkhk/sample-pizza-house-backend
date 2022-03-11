package com.pizzahouse.common.model;

import java.util.List;

public class ConfirmationDetail {
	
	private String pizzaTypeUuid;
	private String subItemReferenceUuid;
	private int subItemCategoryId;
	private int quantity;
	private float singlePrice;
	private float subTotal;
	public String getPizzaTypeUuid() {
		return pizzaTypeUuid;
	}
	public void setPizzaTypeUuid(String pizzaTypeUuid) {
		this.pizzaTypeUuid = pizzaTypeUuid;
	}
	public String getSubItemReferenceUuid() {
		return subItemReferenceUuid;
	}
	public void setSubItemReferenceUuid(String subItemReferenceUuid) {
		this.subItemReferenceUuid = subItemReferenceUuid;
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
