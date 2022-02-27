package com.pizzahouse.service.initialization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.pizzahouse.common.database.DatabaseQuery;
import com.pizzahouse.service.entity.Pizza;
import com.pizzahouse.service.model.FlattenPizzaSize;
import com.pizzahouse.service.model.FlattenPizzaTopping;

@Component
public class DataLoader implements ApplicationRunner {
 	public static Map<String, FlattenPizzaSize> pizzaSizeMap = new HashMap<String, FlattenPizzaSize>();
 	public static Map<String, FlattenPizzaTopping> pizzaToppingMap = new HashMap<String, FlattenPizzaTopping>();

    public void run(ApplicationArguments args) {
    	DatabaseQuery<Pizza> pizzaQuery = new DatabaseQuery<Pizza>();
    	
    	List<Pizza> PizzaList = pizzaQuery.selectAll(Pizza.class);
    	
		for (Pizza pizza : PizzaList) {
			FlattenPizzaSize flattenPizzaSize = new FlattenPizzaSize();
			flattenPizzaSize.setPizzaSize(pizza.getPizzaSize());
			flattenPizzaSize.setPizzaSizeId(pizza.getPizzaSizeId());
			flattenPizzaSize.setPizzaType(pizza.getPizzaType());
			flattenPizzaSize.setPizzaTypeId(pizza.getPizzaTypeId());
			pizzaSizeMap.put(pizza.getPizzaTypeId() + "," + pizza.getPizzaSizeId(), flattenPizzaSize);

			FlattenPizzaTopping flattenPizzaTopping = new FlattenPizzaTopping();
			flattenPizzaTopping.setPizzaTopping(pizza.getPizzaTopping());
			flattenPizzaTopping.setPizzaToppingId(pizza.getPizzaToppingId());
			flattenPizzaTopping.setPizzaType(pizza.getPizzaType());
			flattenPizzaTopping.setPizzaTypeId(pizza.getPizzaTypeId());
			pizzaToppingMap.put(pizza.getPizzaTypeId() + "," + pizza.getPizzaToppingId(), flattenPizzaTopping);

		}
		
    	
  /*    DatabaseQuery<FlattenPizzaSize> pizzaSizeQuery = new DatabaseQuery<FlattenPizzaSize>();
    	DatabaseQuery<FlattenPizzaTopping> pizzaToppingQuery = new DatabaseQuery<FlattenPizzaTopping>();
    	
    	
    	
      	List<FlattenPizzaSize> pizzaSizeList = pizzaSizeQuery.selectAll(FlattenPizzaSize.class);
    	List<FlattenPizzaTopping> pizzaToppingList = pizzaToppingQuery.selectAll(FlattenPizzaTopping.class);
    	
		for (FlattenPizzaSize pizzaSizeItem : pizzaSizeList) {
			pizzaSizeMap.put(pizzaSizeItem.getPizzaTypeId() + "," + pizzaSizeItem.getPizzaSizeId(), pizzaSizeItem);
		}
		
		for (FlattenPizzaTopping pizzaToppingItem : pizzaToppingList) {
			pizzaToppingMap.put(pizzaToppingItem.getPizzaTypeId() + "," + pizzaToppingItem.getPizzaToppingId(), pizzaToppingItem);
		} */
    }
    
    

}
