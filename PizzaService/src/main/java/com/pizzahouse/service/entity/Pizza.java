package com.pizzahouse.service.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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
// @IdClass(CompositePizzaKey.class)
@Table(name="PIZZA")
public class Pizza {

	@EmbeddedId
	CompositePizzaKey key;
	
	@OneToOne
    @JoinColumn(name = "PIZZA_TYPE_ID", insertable=false, updatable=false)
	private PizzaType pizzaType;
    
	@OneToOne
    @JoinColumn(name = "PIZZA_SIZE_ID", insertable=false, updatable=false)
	private PizzaSize pizzaSize;
    
	@OneToOne
    @JoinColumn(name = "PIZZA_TOPPING_ID", insertable=false, updatable=false)
	private PizzaTopping pizzaTopping;


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
