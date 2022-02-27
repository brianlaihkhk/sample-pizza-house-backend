package com.pizzahouse.service.model;

import com.pizzahouse.service.entity.PizzaSize;
import com.pizzahouse.service.entity.PizzaType;

public class FlattenPizzaSize {

	private int pizzaTypeId;

	private int pizzaSizeId;
	
	private PizzaType pizzaType;
    
	private PizzaSize pizzaSize;
    

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

}
