package com.pizzahouse.domain.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="SESSION")
public class Session {

	// no need to join with user table
    @Column(name="USER_ID", nullable=false)
	private int id;

    @Column(name="SESSION_TOKEN", nullable=false)
	private String token;

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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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