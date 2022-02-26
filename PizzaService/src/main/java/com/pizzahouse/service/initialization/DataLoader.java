package com.pizzahouse.service.initialization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.pizzahouse.common.database.DatabaseQuery;
import com.pizzahouse.service.entity.FlattenPizzaSize;
import com.pizzahouse.service.entity.FlattenPizzaTopping;

@Component
public class DataLoader implements ApplicationRunner {
 	public static Map<String, FlattenPizzaSize> pizzaSizeMap = new HashMap<String, FlattenPizzaSize>();
 	public static Map<String, FlattenPizzaTopping> pizzaToppingMap = new HashMap<String, FlattenPizzaTopping>();

    public void run(ApplicationArguments args) {
    	DatabaseQuery<FlattenPizzaSize> pizzaSizeQuery = new DatabaseQuery<FlattenPizzaSize>();
    	DatabaseQuery<FlattenPizzaTopping> pizzaToppingQuery = new DatabaseQuery<FlattenPizzaTopping>();
    	
    	List<FlattenPizzaSize> pizzaSizeList = pizzaSizeQuery.selectAll(FlattenPizzaSize.class);
    	List<FlattenPizzaTopping> pizzaToppingList = pizzaToppingQuery.selectAll(FlattenPizzaTopping.class);
    	
		for (FlattenPizzaSize pizzaSizeItem : pizzaSizeList) {
			pizzaSizeMap.put(pizzaSizeItem.getPizzaTypeId() + "," + pizzaSizeItem.getPizzaSizeId(), pizzaSizeItem);
		}
		
		for (FlattenPizzaTopping pizzaToppingItem : pizzaToppingList) {
			pizzaToppingMap.put(pizzaToppingItem.getPizzaTypeId() + "," + pizzaToppingItem.getPizzaToppingId(), pizzaToppingItem);
		}
    }
    
    

}
