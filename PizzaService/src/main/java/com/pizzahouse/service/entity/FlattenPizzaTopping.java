package com.pizzahouse.service.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PIZZA")
public class FlattenPizzaTopping {

	@Column(name="PIZZA_TYPE_ID")
	private int pizzaTypeId;

	@Column(name="PIZZA_TOPPING_ID")
	private int pizzaToppingId;
	
    @JoinColumn(name = "PIZZA_TYPE_ID")
	private PizzaType pizzaType;
   
    
    @JoinColumn(name = "PIZZA_TOPPING_ID")
	private PizzaTopping pizzaTopping;

	public int getPizzaTypeId() {
		return pizzaTypeId;
	}

	public void setPizzaTypeId(int pizzaTypeId) {
		this.pizzaTypeId = pizzaTypeId;
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

	public PizzaTopping getPizzaTopping() {
		return pizzaTopping;
	}

	public void setPizzaTopping(PizzaTopping pizzaTopping) {
		this.pizzaTopping = pizzaTopping;
	}
	
	
	

}
