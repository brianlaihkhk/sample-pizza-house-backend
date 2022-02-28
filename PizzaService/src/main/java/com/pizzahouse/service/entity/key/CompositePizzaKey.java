package com.pizzahouse.service.entity.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class CompositePizzaKey  implements Serializable {

	@Column(name="PIZZA_TYPE_ID")
	private int pizzaTypeId;
	
	@Column(name="PIZZA_SIZE_ID")
	private int pizzaSizeId;
	
	@Column(name="PIZZA_TOPPING_ID")
	private int pizzaToppingId;

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
	
	
}
