package com.pizzahouse.service.model;

import com.pizzahouse.service.entity.PizzaSize;
import com.pizzahouse.service.entity.PizzaType;

public class FlattenPizzaSize {

	private String pizzaTypeUuid;

	private String pizzaSizeUuid;
	
	private PizzaType pizzaType;
    
	private PizzaSize pizzaSize;
    

	public String getPizzaTypeId() {
		return pizzaTypeUuid;
	}

	public void setPizzaTypeId(String pizzaTypeUuid) {
		this.pizzaTypeUuid = pizzaTypeUuid;
	}

	public String getPizzaSizeId() {
		return pizzaSizeUuid;
	}

	public void setPizzaSizeId(String pizzaSizeUuid) {
		this.pizzaSizeUuid = pizzaSizeUuid;
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
