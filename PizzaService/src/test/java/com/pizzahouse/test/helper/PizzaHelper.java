package com.pizzahouse.test.helper;

import com.pizzahouse.service.entity.PizzaSize;
import com.pizzahouse.service.entity.PizzaSizeFlatten;
import com.pizzahouse.service.entity.PizzaTopping;
import com.pizzahouse.service.entity.PizzaToppingFlatten;
import com.pizzahouse.service.entity.PizzaType;

public class PizzaHelper {
	
	/**
	 * Create Pizza Size Flatten object
	 * @param pizzaType Pizza type of the pizza
	 * @param pizzaSize Pizza size
	 * @return PizzaSizeFlatten object
	 */
 	public static PizzaSizeFlatten createPizzaSizeFlatten(PizzaType pizzaType, PizzaSize pizzaSize){
 		PizzaSizeFlatten pizzaSizeFlatten = new PizzaSizeFlatten();
 		pizzaSizeFlatten.setPizzaSize(pizzaSize);
 		pizzaSizeFlatten.setPizzaType(pizzaType);
 		pizzaSizeFlatten.setPizzaSizeId(pizzaSize.getId());
 		pizzaSizeFlatten.setPizzaTypeId(pizzaType.getId());
 		return pizzaSizeFlatten;
    }

	/**
	 * Create Pizza Size Flatten object
	 * @param pizzaType Pizza type of the pizza
	 * @param pizzaSize Pizza size
	 * @return PizzaSizeFlatten object
	 */
 	public static PizzaToppingFlatten createPizzaToppingFlatten(PizzaType pizzaType, PizzaTopping pizzaTopping){
 		PizzaToppingFlatten pizzaToppingFlatten = new PizzaToppingFlatten();
 		pizzaToppingFlatten.setPizzaTopping(pizzaTopping);
 		pizzaToppingFlatten.setPizzaType(pizzaType);
 		pizzaToppingFlatten.setPizzaToppingId(pizzaTopping.getId());
 		pizzaToppingFlatten.setPizzaTypeId(pizzaType.getId());
 		return pizzaToppingFlatten;
    }	
 	
	/**
	 * Create Pizza Size object information
	 * @param sizeId Pizza Size ID
	 * @param sizeName Size item name
	 * @param sizeDescription Size item description
	 * @param sizePrice How much of the pizza size
	 * @return PizzaTopping object
	 */
 	public static PizzaSize createPizzaSize(int sizeId, String sizeName, String sizeDescription, float sizePrice){
 		PizzaSize pizzaSize = new PizzaSize();
 		pizzaSize.setId(sizeId);
 		pizzaSize.setName(sizeName);
 		pizzaSize.setDescription(sizeDescription);
 		pizzaSize.setPrice(sizePrice);
 		return pizzaSize;
    }

	/**
	 * Create Pizza Type object information
	 * @param typeId Pizza type ID
	 * @param typeName Pizza type name
	 * @param typeDescription Type item description
	 * @return PizzaTopping object
	 */
 	public static PizzaType createPizzaType(int typeId, String typeName, String typeDescription){
 		PizzaType pizzaType = new PizzaType();
 		pizzaType.setId(typeId);
 		pizzaType.setName(typeName);
 		pizzaType.setDescription(typeDescription);
 		return pizzaType;
    }
 
	/**
	 * Create Pizza Topping object information
	 * @param toppingId Pizza Topping ID
	 * @param toppingName Topping item name
	 * @param toppingDescription Topping item description
	 * @param toppingPrice How much of the topping
	 * @return PizzaTopping object
	 */
 	public static PizzaTopping createPizzaTopping(int toppingId, String toppingName, String toppingDescription, float toppingPrice){
 		PizzaTopping pizzaTopping = new PizzaTopping();
 		pizzaTopping.setId(toppingId);
 		pizzaTopping.setName(toppingName);
 		pizzaTopping.setDescription(toppingDescription);
 		pizzaTopping.setPrice(toppingPrice);

 		return pizzaTopping;
    }
}
