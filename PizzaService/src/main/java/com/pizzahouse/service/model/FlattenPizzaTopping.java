package com.pizzahouse.service.model;


import com.pizzahouse.service.entity.PizzaTopping;
import com.pizzahouse.service.entity.PizzaType;


public class FlattenPizzaTopping {

	private String pizzaTypeUuid;

	private String pizzaToppingUuid;
	
	private PizzaType pizzaType;
   
	private PizzaTopping pizzaTopping;

	public String getPizzaTypeId() {
		return pizzaTypeUuid;
	}

	public void setPizzaTypeId(String pizzaTypeUuid) {
		this.pizzaTypeUuid = pizzaTypeUuid;
	}

	public String getPizzaToppingId() {
		return pizzaToppingUuid;
	}

	public void setPizzaToppingId(String pizzaToppingUuid) {
		this.pizzaToppingUuid = pizzaToppingUuid;
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
