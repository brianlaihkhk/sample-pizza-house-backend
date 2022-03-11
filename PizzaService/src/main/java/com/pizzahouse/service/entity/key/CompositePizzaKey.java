package com.pizzahouse.service.entity.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class CompositePizzaKey  implements Serializable {

	@Column(name="PIZZA_TYPE_UUID")
	private String pizzaTypeUuid;
	
	@Column(name="PIZZA_SIZE_UUID")
	private String pizzaSizeUuid;
	
	@Column(name="PIZZA_TOPPING_UUID")
	private String pizzaToppingUuid;

	public String getPizzaTypeId() {
		return pizzaTypeUuid;
	}

	public void setPizzaTypeUuid(String pizzaTypeUuid) {
		this.pizzaTypeUuid = pizzaTypeUuid;
	}

	public String getPizzaSizeUuid() {
		return pizzaSizeUuid;
	}

	public void setPizzaSizeUuid(String pizzaSizeUuid) {
		this.pizzaSizeUuid = pizzaSizeUuid;
	}

	public String getPizzaToppingUuid() {
		return pizzaToppingUuid;
	}

	public void setPizzaToppingUuid(String pizzaToppingUuid) {
		this.pizzaToppingUuid = pizzaToppingUuid;
	}
	
	
}
