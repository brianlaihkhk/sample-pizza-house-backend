package com.pizzahouse.order.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;

import com.pizzahouse.order.entity.PurchaseDetail;

@Entity
@Table(name="PURCHASE")
public class Purchase {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PURCHASE_ID")
	private int id;

    @Column(name = "USER_ID")
	private int userId;

    @Column(name="TOTAL_AMOUNT", scale=2, nullable=false)
	private float totalAmount;

    /*
     * TODO: Unknown error raised by Hibernate ORM when using OneToMany
     *       Using a work around to split insert of Purchase and PurchaseDetails
     */
//    @OneToMany(cascade = {CascadeType.ALL})
//    @JoinColumn(name="PURCHASE_ID")
//	private List<PurchaseDetail> details = new ArrayList<PurchaseDetail>();

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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

//	public List<PurchaseDetail> getDetails() {
//		return details;
//	}
//
//	public void setDetails(List<PurchaseDetail> details) {
//		this.details = details;
//	}

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