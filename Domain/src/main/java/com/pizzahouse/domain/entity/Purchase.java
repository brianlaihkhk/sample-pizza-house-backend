package com.pizzahouse.domain.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pizzahouse.domain.entity.User;

@Entity
@Table(name="PURCHASE")
public class Purchase {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PURCHASE_ID")
	private int id;
	
	private User user;

    @Column(name="AMOUNT", scale=2, nullable=false)
	private float amount;
	
	private PurchaseDetail detail;

	@Column(name = "CREATION_TIME", columnDefinition="DATETIME")
	private Date creationTime;

	@Column(name="CREATION_EPOCH_TIME")
	private long creationEpochTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public PurchaseDetail getDetail() {
		return detail;
	}

	public void setDetail(PurchaseDetail detail) {
		this.detail = detail;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public long getCreationEpochTime() {
		return creationEpochTime;
	}

	public void setCreationEpochTime(long creationEpochTime) {
		this.creationEpochTime = creationEpochTime;
	}

	
}