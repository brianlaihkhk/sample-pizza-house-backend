package com.pizzahouse.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PIZZA_SIZE")
public class PizzaSize {
	
	@Id
	@Column(name="PIZZA_SIZE_UUID")
	private String sizeUuid;

    @Column(name="NAME", length=100, nullable=false)
	private String name;

    @Column(name="DESCRIPTION", length=1000, nullable=false)
	private String description;
    
    @Column(name="PRICE", scale=2, nullable=false)
	private float price;
    
	public String getSizeUuid() {
		return sizeUuid;
	}
	public void setSizeUuid(String sizeUuid) {
		this.sizeUuid = sizeUuid;
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}		

}
