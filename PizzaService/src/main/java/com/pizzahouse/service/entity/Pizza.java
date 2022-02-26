package com.pizzahouse.service.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PIZZA")
public class Pizza {

	@JoinColumn(name = "PIZZA_TYPE_ID")
	private PizzaType pizzaType;
    
    @OneToMany
    @JoinColumn(name = "PIZZA_SIZE_ID")
	private List<PizzaSize> pizzaSizeList;
    
    @OneToMany
    @JoinColumn(name = "PIZZA_TOPPING_ID")
	private List<PizzaTopping> pizzaToppingList;
	
	public PizzaType getPizzaType() {
		return pizzaType;
	}
	public void setPizzaType(PizzaType pizzaType) {
		this.pizzaType = pizzaType;
	}
	public List<PizzaSize> getPizzaSizeList() {
		return pizzaSizeList;
	}
	public void setPizzaSizeList(List<PizzaSize> pizzaSizeList) {
		this.pizzaSizeList = pizzaSizeList;
	}
	public List<PizzaTopping> getPizzaToppingList() {
		return pizzaToppingList;
	}
	public void setPizzaToppingList(List<PizzaTopping> pizzaToppingList) {
		this.pizzaToppingList = pizzaToppingList;
	}
	
	

}
