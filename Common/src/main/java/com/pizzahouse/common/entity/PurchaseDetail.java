package com.pizzahouse.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PURCHASE_DETAIL")
public class PurchaseDetail {

	private Pizza pizza;
	
    @Column(name="PRICE", scale=2, nullable=false)
	private float price;
    
    @Column(name="QUANTITY", nullable=false)
	private int quantity;
	
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}