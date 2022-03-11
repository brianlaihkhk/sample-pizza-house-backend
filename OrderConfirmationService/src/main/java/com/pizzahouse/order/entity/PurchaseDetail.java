package com.pizzahouse.order.entity;

import java.util.UUID;

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
	@Column(name="PURCHASE_DETAIL_UUID", unique = true, nullable = false)
	private String purchaseDetailUuid;

	@Column(name="PURCHASE_UUID")
	private String purchaseUuid;
	
	@Column(name="PIZZA_TYPE_UUID")
	private String pizzaTypeUuid;

	@Column(name="SUB_ITEM_REFERENCE_UUID")
	private String subItemReferenceUuid;
	
	@Column(name="SUB_ITEM_CATEGORY_ID")
	private int subItemCategoryId;
	
    @Column(name="QUANTITY", nullable=false)
	private int quantity;
	
    @Column(name="SINGLE_PRICE", scale=2, nullable=false)
	private float singlePrice;

    @Column(name="SUB_TOTAL", scale=2, nullable=false)
	private float subTotal;
    
    public PurchaseDetail() {
    	setPurchaseDetailUuid(UUID.randomUUID().toString());
    }
    
	public String getPurchaseDetailUuid() {
		return purchaseDetailUuid;
	}

	public void setPurchaseDetailUuid(String purchaseDetailUuid) {
		this.purchaseDetailUuid = purchaseDetailUuid;
	}

	public String getPurchaseUuid() {
		return purchaseUuid;
	}

	public void setPurchaseUuid(String purchaseUuid) {
		this.purchaseUuid = purchaseUuid;
	}

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