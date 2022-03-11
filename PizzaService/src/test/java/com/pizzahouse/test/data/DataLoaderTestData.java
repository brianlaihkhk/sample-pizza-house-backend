package com.pizzahouse.test.data;

import java.util.HashMap;
import java.util.Map;

import com.pizzahouse.service.entity.PizzaSize;
import com.pizzahouse.service.entity.PizzaTopping;
import com.pizzahouse.service.entity.PizzaType;
import com.pizzahouse.service.model.FlattenPizzaSize;
import com.pizzahouse.service.model.FlattenPizzaTopping;
import com.pizzahouse.test.helper.PizzaHelper;

public class DataLoaderTestData {

 	private static PizzaType neapolitanPizza = PizzaHelper.createPizzaType(OrderTestData.neapolianPizzaUuid, "Neapolitan Pizza", "The true original, topped with sauce from fresh tomatoes olive oil and minimal mozzarella.");
 	private static PizzaType chicagoPizza = PizzaHelper.createPizzaType(OrderTestData.chicagoPizzaUuid, "Chicago Pizza", "Prepared as the thick, classic deep-dish pizza. Thin-medium crust containing cornmeal or semolina.");
 	private static PizzaType newYorkPizza = PizzaHelper.createPizzaType(OrderTestData.newYorkPizzaUuid, "New-York Pizza", "Large, foldable slices, crispy outer crust. Traditional toppings of tomato sauce and mozzarella cheese.");
 	private static PizzaType californiaPizza = PizzaHelper.createPizzaType(OrderTestData.californiaPizzaUuid, "California Pizza", "A single-serving, thin crust pizza. Popular due to its creative, non-traditional toppings, like chicken, egg, artichokes, salmon, feta or goat cheese.");

 	private static PizzaSize roundRegularSize = PizzaHelper.createPizzaSize(OrderTestData.roundRegularUuid, "Round Regular", "Round Regular Size", 99);
 	private static PizzaSize roundLargeSize = PizzaHelper.createPizzaSize(OrderTestData.roundLargeUuid, "Round Large", "Round Large Size", 129);
 	private static PizzaSize squareRegularSize = PizzaHelper.createPizzaSize(OrderTestData.squareRegularUuid, "Square Regular", "Square Regular Size", 119);
 	private static PizzaSize squareLargeSize = PizzaHelper.createPizzaSize(OrderTestData.squareLargeUuid, "Square Large", "Square Large Size", 149);

 	private static PizzaTopping chickenTopping = PizzaHelper.createPizzaTopping(OrderTestData.chickenUuid, "Chicken", "Chicken Topping", 15);
 	private static PizzaTopping eggTopping = PizzaHelper.createPizzaTopping(OrderTestData.eggUuid, "Egg", "Egg Topping", 15);
 	private static PizzaTopping goatCheeseTopping = PizzaHelper.createPizzaTopping(OrderTestData.goatCheeseUuid, "Goat Cheese", "Goat Cheese Topping", 25);
 	private static PizzaTopping salmonTopping = PizzaHelper.createPizzaTopping(OrderTestData.salmonUuid, "Salmon", "Salmon Topping", 25);
 	private static PizzaTopping beefTopping = PizzaHelper.createPizzaTopping(OrderTestData.beefUuid, "Beef", "Beef Topping", 20);

	/**
	 * Generate PizzaSizeMap Test Data Set 1
	 */
 	public static Map<String, FlattenPizzaSize> generatePizzaSizeMapSet1(){
 		Map<String, FlattenPizzaSize> map = new HashMap<String, FlattenPizzaSize>();
 		map.put(OrderTestData.neapolianPizzaUuid + "," + OrderTestData.roundRegularUuid, PizzaHelper.createPizzaSizeFlatten(neapolitanPizza, roundRegularSize));
 		map.put(OrderTestData.neapolianPizzaUuid + "," + OrderTestData.roundLargeUuid, PizzaHelper.createPizzaSizeFlatten(neapolitanPizza, roundLargeSize));
 		map.put(OrderTestData.neapolianPizzaUuid + "," + OrderTestData.squareLargeUuid, PizzaHelper.createPizzaSizeFlatten(neapolitanPizza, squareLargeSize));
 		map.put(OrderTestData.neapolianPizzaUuid + "," + OrderTestData.squareRegularUuid, PizzaHelper.createPizzaSizeFlatten(neapolitanPizza, squareRegularSize));
 		
 		map.put(OrderTestData.chicagoPizzaUuid + "," + OrderTestData.roundRegularUuid, PizzaHelper.createPizzaSizeFlatten(chicagoPizza, roundRegularSize));
 		
 		map.put(OrderTestData.newYorkPizzaUuid + "," + OrderTestData.roundRegularUuid, PizzaHelper.createPizzaSizeFlatten(newYorkPizza, roundRegularSize));
 		map.put(OrderTestData.newYorkPizzaUuid + "," + OrderTestData.roundLargeUuid, PizzaHelper.createPizzaSizeFlatten(newYorkPizza, roundLargeSize));
 		
 		map.put(OrderTestData.californiaPizzaUuid + "," + OrderTestData.roundRegularUuid, PizzaHelper.createPizzaSizeFlatten(californiaPizza, roundRegularSize));
 		map.put(OrderTestData.californiaPizzaUuid + "," + OrderTestData.roundLargeUuid, PizzaHelper.createPizzaSizeFlatten(californiaPizza, roundLargeSize));
 		map.put(OrderTestData.californiaPizzaUuid + "," + OrderTestData.squareRegularUuid, PizzaHelper.createPizzaSizeFlatten(californiaPizza, squareRegularSize));
 		map.put(OrderTestData.californiaPizzaUuid + "," + OrderTestData.squareLargeUuid, PizzaHelper.createPizzaSizeFlatten(californiaPizza, squareLargeSize));
 		return map;
 	}
 
	/**
	 * Generate PizzaToppingMap Test Data Set 1
	 */
 	public static Map<String, FlattenPizzaTopping> generatePizzaToppingMapSet1(){
 		Map<String, FlattenPizzaTopping> map = new HashMap<String, FlattenPizzaTopping>();
 		map.put(OrderTestData.neapolianPizzaUuid + "," + OrderTestData.chickenUuid, PizzaHelper.createPizzaToppingFlatten(neapolitanPizza, chickenTopping));
 		map.put(OrderTestData.neapolianPizzaUuid + "," + OrderTestData.eggUuid, PizzaHelper.createPizzaToppingFlatten(neapolitanPizza, eggTopping));
 		map.put(OrderTestData.neapolianPizzaUuid + "," + OrderTestData.goatCheeseUuid, PizzaHelper.createPizzaToppingFlatten(neapolitanPizza, goatCheeseTopping));
 		map.put(OrderTestData.neapolianPizzaUuid + "," + OrderTestData.salmonUuid, PizzaHelper.createPizzaToppingFlatten(neapolitanPizza, salmonTopping));
 		map.put(OrderTestData.neapolianPizzaUuid + "," + OrderTestData.beefUuid, PizzaHelper.createPizzaToppingFlatten(neapolitanPizza, beefTopping));
 		
 		map.put(OrderTestData.chicagoPizzaUuid + "," + OrderTestData.chickenUuid, PizzaHelper.createPizzaToppingFlatten(chicagoPizza, chickenTopping));
 		map.put(OrderTestData.chicagoPizzaUuid + "," + OrderTestData.eggUuid, PizzaHelper.createPizzaToppingFlatten(chicagoPizza, eggTopping));
 		map.put(OrderTestData.chicagoPizzaUuid + "," + OrderTestData.salmonUuid, PizzaHelper.createPizzaToppingFlatten(chicagoPizza, salmonTopping));
 		
 		map.put(OrderTestData.newYorkPizzaUuid + "," + OrderTestData.chickenUuid, PizzaHelper.createPizzaToppingFlatten(newYorkPizza, chickenTopping));
 		map.put(OrderTestData.newYorkPizzaUuid + "," + OrderTestData.eggUuid, PizzaHelper.createPizzaToppingFlatten(newYorkPizza, eggTopping));
 		map.put(OrderTestData.newYorkPizzaUuid + "," + OrderTestData.goatCheeseUuid, PizzaHelper.createPizzaToppingFlatten(newYorkPizza, goatCheeseTopping));
 		
 		map.put(OrderTestData.californiaPizzaUuid + "," + OrderTestData.chickenUuid, PizzaHelper.createPizzaToppingFlatten(californiaPizza, chickenTopping));
 		map.put(OrderTestData.californiaPizzaUuid + "," + OrderTestData.eggUuid, PizzaHelper.createPizzaToppingFlatten(californiaPizza, eggTopping));
 		map.put(OrderTestData.californiaPizzaUuid + "," + OrderTestData.goatCheeseUuid, PizzaHelper.createPizzaToppingFlatten(californiaPizza, goatCheeseTopping));
 		map.put(OrderTestData.californiaPizzaUuid + "," + OrderTestData.beefUuid, PizzaHelper.createPizzaToppingFlatten(californiaPizza, beefTopping));
 		return map;
 	}
}
