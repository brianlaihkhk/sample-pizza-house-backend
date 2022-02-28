package com.pizzahouse.service.initialization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pizzahouse.common.exception.DatabaseUnavailableException;
import com.pizzahouse.service.database.DatabaseQuery;
import com.pizzahouse.service.entity.Pizza;
import com.pizzahouse.service.model.FlattenPizzaSize;
import com.pizzahouse.service.model.FlattenPizzaTopping;

@Component
public class DataLoader implements ApplicationRunner {
	@Autowired
	protected DatabaseQuery<Pizza> pizzaQuery;
	@Autowired
	protected Logger logger;
	
 	public static Map<String, FlattenPizzaSize> pizzaSizeMap = new HashMap<String, FlattenPizzaSize>();
 	public static Map<String, FlattenPizzaTopping> pizzaToppingMap = new HashMap<String, FlattenPizzaTopping>();

	public void run(ApplicationArguments args) throws DatabaseUnavailableException {
    	logger.info("DataLoader start");

    	List<Pizza> pizzaList = pizzaQuery.selectAll(Pizza.class);
    	
  	
		for (Pizza pizza : pizzaList) {
			FlattenPizzaSize flattenPizzaSize = new FlattenPizzaSize();
			flattenPizzaSize.setPizzaSize(pizza.getPizzaSize());
			flattenPizzaSize.setPizzaSizeId(pizza.getPizzaSize().getSizeId());
			flattenPizzaSize.setPizzaType(pizza.getPizzaType());
			flattenPizzaSize.setPizzaTypeId(pizza.getPizzaType().getTypeId());
			pizzaSizeMap.put(pizza.getPizzaType().getTypeId() + "," + pizza.getPizzaSize().getSizeId(), flattenPizzaSize);

			FlattenPizzaTopping flattenPizzaTopping = new FlattenPizzaTopping();
			flattenPizzaTopping.setPizzaTopping(pizza.getPizzaTopping());
			flattenPizzaTopping.setPizzaToppingId(pizza.getPizzaTopping().getToppingId());
			flattenPizzaTopping.setPizzaType(pizza.getPizzaType());
			flattenPizzaTopping.setPizzaTypeId(pizza.getPizzaType().getTypeId());
			pizzaToppingMap.put(pizza.getPizzaType().getTypeId() + "," + pizza.getPizzaTopping().getToppingId(), flattenPizzaTopping);

		}

    	logger.info("DataLoader end");

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
