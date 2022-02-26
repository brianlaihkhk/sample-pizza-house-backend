package com.pizzahouse.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PURCHASE_DETAIL")
public class PurchaseDetail {

	@Column(name="PURCHASE_ID")
	private int id;

    @Column(name="ITEM_NAME", nullable=false)
	private String name;

    @Column(name="ITEM_DESCRIPTION", nullable=false)
	private String description;

    @Column(name="QUANTITY", nullable=false)
	private int quantity;
	
    @Column(name="SINGLE_PRICE", scale=2, nullable=false)
	private float singlePrice;

    @Column(name="SUB_TOTAL", scale=2, nullable=false)
	private float subTotal;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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