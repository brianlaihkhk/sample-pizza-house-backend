package com.pizzahouse.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PIZZA_TYPE")
public class PizzaType {
	
	@Id
	@Column(name="PIZZA_TYPE_UUID", nullable=false)
	private String typeUuid;

    @Column(name="NAME", length=100, nullable=false)
	private String name;

    @Column(name="DESCRIPTION", length=1000, nullable=false)
	private String description;
    
	public String getTypeUuid() {
		return typeUuid;
	}
	public void setTypeUuid(String typeUuid) {
		this.typeUuid = typeUuid;
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

}
