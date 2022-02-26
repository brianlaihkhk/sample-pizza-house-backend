package com.pizzahouse.test.data;

import java.util.HashMap;
import java.util.Map;

import com.pizzahouse.service.entity.PizzaSize;
import com.pizzahouse.service.entity.FlattenPizzaSize;
import com.pizzahouse.service.entity.PizzaTopping;
import com.pizzahouse.service.entity.FlattenPizzaTopping;
import com.pizzahouse.service.entity.PizzaType;
import com.pizzahouse.test.helper.PizzaHelper;

public class DataLoaderTestData {

 	private static PizzaType neapolitanPizza = PizzaHelper.createPizzaType(1, "Neapolitan Pizza", "The true original, topped with sauce from fresh tomatoes olive oil and minimal mozzarella.");
 	private static PizzaType chicagoPizza = PizzaHelper.createPizzaType(2, "Chicago Pizza", "Prepared as the thick, classic deep-dish pizza. Thin-medium crust containing cornmeal or semolina.");
 	private static PizzaType newYorkPizza = PizzaHelper.createPizzaType(3, "New-York Pizza", "Large, foldable slices, crispy outer crust. Traditional toppings of tomato sauce and mozzarella cheese.");
 	private static PizzaType californiaPizza = PizzaHelper.createPizzaType(4, "California Pizza", "A single-serving, thin crust pizza. Popular due to its creative, non-traditional toppings, like chicken, egg, artichokes, salmon, feta or goat cheese.");

 	private static PizzaSize roundRegularSize = PizzaHelper.createPizzaSize(1, "Round Regular", "Round Regular Size", 99);
 	private static PizzaSize roundLargeSize = PizzaHelper.createPizzaSize(2, "Round Large", "Round Large Size", 129);
 	private static PizzaSize SquareRegularSize = PizzaHelper.createPizzaSize(3, "Square Regular", "Square Regular Size", 119);
 	private static PizzaSize SquareLargeSize = PizzaHelper.createPizzaSize(4, "Square Large", "Square Large Size", 149);

 	private static PizzaTopping chickenTopping = PizzaHelper.createPizzaTopping(1, "Chicken", "Chicken Topping", 15);
 	private static PizzaTopping eggTopping = PizzaHelper.createPizzaTopping(2, "Egg", "Egg Topping", 15);
 	private static PizzaTopping goatCheeseTopping = PizzaHelper.createPizzaTopping(3, "Goat Cheese", "Goat Cheese Topping", 25);
 	private static PizzaTopping salmonTopping = PizzaHelper.createPizzaTopping(4, "Salmon", "Salmon Topping", 25);
 	private static PizzaTopping beefTopping = PizzaHelper.createPizzaTopping(5, "Beef", "Beef Topping", 20);

	/**
	 * Generate PizzaSizeMap Test Data Set 1
	 */
 	public static Map<String, FlattenPizzaSize> generatePizzaSizeMapSet1(){
 		Map<String, FlattenPizzaSize> map = new HashMap<String, FlattenPizzaSize>();
 		map.put("1,1", PizzaHelper.createPizzaSizeFlatten(neapolitanPizza, roundRegularSize));
 		map.put("1,2", PizzaHelper.createPizzaSizeFlatten(neapolitanPizza, roundLargeSize));
 		map.put("1,4", PizzaHelper.createPizzaSizeFlatten(neapolitanPizza, SquareLargeSize));
 		map.put("2,1", PizzaHelper.createPizzaSizeFlatten(chicagoPizza, roundRegularSize));
 		map.put("2,4", PizzaHelper.createPizzaSizeFlatten(chicagoPizza, SquareLargeSize));
 		map.put("3,1", PizzaHelper.createPizzaSizeFlatten(newYorkPizza, roundRegularSize));
 		map.put("3,2", PizzaHelper.createPizzaSizeFlatten(newYorkPizza, roundLargeSize));
 		map.put("4,1", PizzaHelper.createPizzaSizeFlatten(californiaPizza, roundRegularSize));
 		map.put("4,2", PizzaHelper.createPizzaSizeFlatten(californiaPizza, roundLargeSize));
 		map.put("4,3", PizzaHelper.createPizzaSizeFlatten(californiaPizza, SquareRegularSize));
 		map.put("4,4", PizzaHelper.createPizzaSizeFlatten(californiaPizza, SquareLargeSize));
 		return map;
 	}
 
	/**
	 * Generate PizzaToppingMap Test Data Set 1
	 */
 	public static Map<String, FlattenPizzaTopping> generatePizzaToppingMapSet1(){
 		Map<String, FlattenPizzaTopping> map = new HashMap<String, FlattenPizzaTopping>();
 		map.put("1,1", PizzaHelper.createPizzaToppingFlatten(neapolitanPizza, chickenTopping));
 		map.put("1,2", PizzaHelper.createPizzaToppingFlatten(neapolitanPizza, eggTopping));
 		map.put("1,3", PizzaHelper.createPizzaToppingFlatten(neapolitanPizza, goatCheeseTopping));
 		map.put("1,4", PizzaHelper.createPizzaToppingFlatten(neapolitanPizza, salmonTopping));
 		map.put("1,5", PizzaHelper.createPizzaToppingFlatten(neapolitanPizza, beefTopping));
 		map.put("2,1", PizzaHelper.createPizzaToppingFlatten(chicagoPizza, chickenTopping));
 		map.put("2,2", PizzaHelper.createPizzaToppingFlatten(chicagoPizza, eggTopping));
 		map.put("2,4", PizzaHelper.createPizzaToppingFlatten(chicagoPizza, salmonTopping));
 		map.put("3,1", PizzaHelper.createPizzaToppingFlatten(newYorkPizza, chickenTopping));
 		map.put("3,2", PizzaHelper.createPizzaToppingFlatten(newYorkPizza, eggTopping));
 		map.put("3,3", PizzaHelper.createPizzaToppingFlatten(newYorkPizza, salmonTopping));
 		map.put("4,1", PizzaHelper.createPizzaToppingFlatten(californiaPizza, chickenTopping));
 		map.put("4,2", PizzaHelper.createPizzaToppingFlatten(californiaPizza, eggTopping));
 		map.put("4,3", PizzaHelper.createPizzaToppingFlatten(californiaPizza, goatCheeseTopping));
 		map.put("4,5", PizzaHelper.createPizzaToppingFlatten(californiaPizza, beefTopping));
 		return map;
 	}
}
