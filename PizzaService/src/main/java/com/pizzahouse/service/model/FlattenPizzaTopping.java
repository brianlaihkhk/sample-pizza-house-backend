package com.pizzahouse.service.model;


import com.pizzahouse.service.entity.PizzaTopping;
import com.pizzahouse.service.entity.PizzaType;


public class FlattenPizzaTopping {

	private int pizzaTypeId;

	private int pizzaToppingId;
	
	private PizzaType pizzaType;
   
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
