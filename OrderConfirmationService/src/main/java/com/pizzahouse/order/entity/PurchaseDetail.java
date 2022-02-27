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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PURCHASE_DETAIL_ID")
	private int purchaseDetailId;

	@Column(name="PURCHASE_ID")
	private int purchaseId;
	
	@Column(name="PIZZA_TYPE_ID")
	private int pizzaTypeId;

	@Column(name="SUB_ITEM_REFERENCE_ID")
	private int subItemReferenceId;
	
	@Column(name="SUB_ITEM_CATEGORY_ID")
	private int subItemCategoryId;
	
    @Column(name="QUANTITY", nullable=false)
	private int quantity;
	
    @Column(name="SINGLE_PRICE", scale=2, nullable=false)
	private float singlePrice;

    @Column(name="SUB_TOTAL", scale=2, nullable=false)
	private float subTotal;
    

	public int getPurchaseDetailId() {
		return purchaseDetailId;
	}

	public void setPurchaseDetailId(int purchaseDetailId) {
		this.purchaseDetailId = purchaseDetailId;
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

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