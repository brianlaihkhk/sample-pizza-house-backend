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
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.hibernate.query.Query;

import com.pizzahouse.common.entity.User;
import com.pizzahouse.common.model.OrderConfirmation;
import java.io.Serializable;

@Entity
@Table(name="PIZZA")
public class Pizza implements Serializable {

	private static final long serialVersionUID = 2993865921902478848L;

	@JoinColumn(name = "PIZZA_TYPE_ID")
	private PizzaType pizzaType;
    
    @OneToMany
    @JoinColumn(name = "PIZZA_SIZE_ID")
	private List<PizzaSize> pizzaSize;
    
    @OneToMany
    @JoinColumn(name = "PIZZA_TOPPING_ID")
	private List<PizzaTopping> pizzaTopping;
	
	public PizzaType getPizzaType() {
		return pizzaType;
	}
	public void setPizzaType(PizzaType pizzaType) {
		this.pizzaType = pizzaType;
	}
	public List<PizzaSize> getPizzaSize() {
		return pizzaSize;
	}
	public void setPizzaSize(List<PizzaSize> pizzaSize) {
		this.pizzaSize = pizzaSize;
	}
	public List<PizzaTopping> getPizzaTopping() {
		return pizzaTopping;
	}
	public void setPizzaTopping(List<PizzaTopping> pizzaTopping) {
		this.pizzaTopping = pizzaTopping;
	}
	
	

}
