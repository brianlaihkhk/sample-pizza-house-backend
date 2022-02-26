package com.pizzahouse.service.initialization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.pizzahouse.common.database.DatabaseQuery;
import com.pizzahouse.service.entity.PizzaSizeFlatten;
import com.pizzahouse.service.entity.PizzaToppingFlatten;

@Component
public class DataLoader implements ApplicationRunner {
 	public static Map<String, PizzaSizeFlatten> pizzaSizeMap = new HashMap<String, PizzaSizeFlatten>();
 	public static Map<String, PizzaToppingFlatten> pizzaToppingMap = new HashMap<String, PizzaToppingFlatten>();

    public void run(ApplicationArguments args) {
    	DatabaseQuery<PizzaSizeFlatten> pizzaSizeQuery = new DatabaseQuery<PizzaSizeFlatten>();
    	DatabaseQuery<PizzaToppingFlatten> pizzaToppingQuery = new DatabaseQuery<PizzaToppingFlatten>();
    	
    	List<PizzaSizeFlatten> pizzaSizeList = pizzaSizeQuery.selectAll(PizzaSizeFlatten.class);
    	List<PizzaToppingFlatten> pizzaToppingList = pizzaToppingQuery.selectAll(PizzaToppingFlatten.class);
    	
		for (PizzaSizeFlatten pizzaSizeItem : pizzaSizeList) {
			pizzaSizeMap.put(pizzaSizeItem.getPizzaTypeId() + "," + pizzaSizeItem.getPizzaSizeId(), pizzaSizeItem);
		}
		
		for (PizzaToppingFlatten pizzaToppingItem : pizzaToppingList) {
			pizzaToppingMap.put(pizzaToppingItem.getPizzaTypeId() + "," + pizzaToppingItem.getPizzaToppingId(), pizzaToppingItem);
		}
    }
    
    

}
