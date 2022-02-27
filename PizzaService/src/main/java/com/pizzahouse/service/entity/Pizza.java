package com.pizzahouse.service.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pizzahouse.service.entity.key.CompositePizzaKey;

@Entity
@IdClass(CompositePizzaKey.class)
@Table(name="PIZZA")
public class Pizza {

	@Id
	@Column(name="PIZZA_TYPE_ID")
	private int pizzaTypeId;

	@Id
	@Column(name="PIZZA_SIZE_ID")
	private int pizzaSizeId;
	
	@Id
	@Column(name="PIZZA_TOPPING_ID")
	private int pizzaToppingId;
	
	@OneToOne
    @JoinColumn(name = "pizzaTypeId")
	private PizzaType pizzaType;
    
	@OneToOne
    @JoinColumn(name = "pizzaSizeId")
	private PizzaSize pizzaSize;
    
	@OneToOne
    @JoinColumn(name = "pizzaToppingId")
	private PizzaTopping pizzaTopping;

	public int getPizzaTypeId() {
		return pizzaTypeId;
	}

	public void setPizzaTypeId(int pizzaTypeId) {
		this.pizzaTypeId = pizzaTypeId;
	}

	public int getPizzaSizeId() {
		return pizzaSizeId;
	}

	public void setPizzaSizeId(int pizzaSizeId) {
		this.pizzaSizeId = pizzaSizeId;
	}

	public int getPizzaToppingId() {
		return pizzaToppingId;
	}

	public void setPizzaToppingId(int pizzaToppingId) {
		this.pizzaToppingId = pizzaToppingId;
	}

	public PizzaType getPizzaType() {
		return pizzaType;
	}

	public void setPizzaType(PizzaType pizzaType) {
		this.pizzaType = pizzaType;
	}

	public PizzaSize getPizzaSize() {
		return pizzaSize;
	}

	public void setPizzaSize(PizzaSize pizzaSize) {
		this.pizzaSize = pizzaSize;
	}

	public PizzaTopping getPizzaTopping() {
		return pizzaTopping;
	}

	public void setPizzaTopping(PizzaTopping pizzaTopping) {
		this.pizzaTopping = pizzaTopping;
	}
	
	
}
