package com.pizzahouse.service.initialization;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.pizzahouse.common.database.DatabaseQuery;
import com.pizzahouse.service.entity.PizzaSizeFlatten;
import com.pizzahouse.service.entity.PizzaToppingFlatten;

@Component
public class DataLoader implements ApplicationRunner {
	private DatabaseQuery<PizzaSizeFlatten> pizzaSizeQuery = new DatabaseQuery<PizzaSizeFlatten>();
	private DatabaseQuery<PizzaToppingFlatten> pizzaToppingQuery = new DatabaseQuery<PizzaToppingFlatten>();
    public static List<PizzaSizeFlatten> pizzaSizeFlatten;
    public static List<PizzaToppingFlatten> pizzaToppingFlatten;

    public void run(ApplicationArguments args) {
    	pizzaSizeFlatten = pizzaSizeQuery.selectAll(PizzaSizeFlatten.class);
    	pizzaToppingFlatten = pizzaToppingQuery.selectAll(PizzaToppingFlatten.class);
    }

}
