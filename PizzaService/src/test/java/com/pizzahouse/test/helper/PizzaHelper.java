package com.pizzahouse.test.helper;

import com.pizzahouse.service.entity.PizzaSize;
import com.pizzahouse.service.entity.PizzaTopping;
import com.pizzahouse.service.entity.PizzaType;
import com.pizzahouse.service.model.FlattenPizzaSize;
import com.pizzahouse.service.model.FlattenPizzaTopping;

public class PizzaHelper {
	
	/**
	 * Create Pizza Size Flatten object
	 * @param pizzaType Pizza type of the pizza
	 * @param pizzaSize Pizza size
	 * @return PizzaSizeFlatten object
	 */
 	public static FlattenPizzaSize createPizzaSizeFlatten(PizzaType pizzaType, PizzaSize pizzaSize){
 		FlattenPizzaSize pizzaSizeFlatten = new FlattenPizzaSize();
 		pizzaSizeFlatten.setPizzaSize(pizzaSize);
 		pizzaSizeFlatten.setPizzaType(pizzaType);
 		pizzaSizeFlatten.setPizzaSizeId(pizzaSize.getSizeUuid());
 		pizzaSizeFlatten.setPizzaTypeId(pizzaType.getTypeUuid());
 		return pizzaSizeFlatten;
    }

	/**
	 * Create Pizza Size Flatten object
	 * @param pizzaType Pizza type of the pizza
	 * @param pizzaSize Pizza size
	 * @return PizzaSizeFlatten object
	 */
 	public static FlattenPizzaTopping createPizzaToppingFlatten(PizzaType pizzaType, PizzaTopping pizzaTopping){
 		FlattenPizzaTopping pizzaToppingFlatten = new FlattenPizzaTopping();
 		pizzaToppingFlatten.setPizzaTopping(pizzaTopping);
 		pizzaToppingFlatten.setPizzaType(pizzaType);
 		pizzaToppingFlatten.setPizzaToppingId(pizzaTopping.getToppingUuid());
 		pizzaToppingFlatten.setPizzaTypeId(pizzaType.getTypeUuid());
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
 	public static PizzaSize createPizzaSize(String sizeId, String sizeName, String sizeDescription, float sizePrice){
 		PizzaSize pizzaSize = new PizzaSize();
 		pizzaSize.setSizeUuid(sizeId);
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
 	public static PizzaType createPizzaType(String typeId, String typeName, String typeDescription){
 		PizzaType pizzaType = new PizzaType();
 		pizzaType.setTypeUuid(typeId);
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
 	public static PizzaTopping createPizzaTopping(String toppingId, String toppingName, String toppingDescription, float toppingPrice){
 		PizzaTopping pizzaTopping = new PizzaTopping();
 		pizzaTopping.setToppingUuid(toppingId);
 		pizzaTopping.setName(toppingName);
 		pizzaTopping.setDescription(toppingDescription);
 		pizzaTopping.setPrice(toppingPrice);

 		return pizzaTopping;
    }
}
