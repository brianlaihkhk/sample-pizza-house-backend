package com.pizzahouse.test.suite;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pizzahouse.common.exception.DatabaseUnavailableException;
import com.pizzahouse.common.exception.OrderFullfillmentException;
import com.pizzahouse.common.exception.UnauthorizedException;
import com.pizzahouse.common.model.Confirmation;
import com.pizzahouse.service.controller.OrderService;
import com.pizzahouse.service.initialization.DataLoader;
import com.pizzahouse.service.model.Order;
import com.pizzahouse.test.data.DataLoaderTestData;
import com.pizzahouse.test.data.OrderTestData;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/applicationContext.xml")
public class OrderServiceTest {
	@Autowired
	protected OrderService orderService;
	@Autowired
	protected Logger logger;

	/**
	 * Populate data to PizzaSizeMap and PizzaToppingMap
	 */
    @BeforeClass
    public static void instantiate() {
    	DataLoader.pizzaSizeMap = DataLoaderTestData.generatePizzaSizeMapSet1();
    	DataLoader.pizzaToppingMap = DataLoaderTestData.generatePizzaToppingMapSet1();
    }
    
	/**
	 * Validate the PizzaSizeMap is working correctly
	 */
    @Test
    public void _01_pizzaSizeMapTest() {
        assertEquals(1, DataLoader.pizzaSizeMap.get("1,2").getPizzaTypeId());
        assertEquals(2, DataLoader.pizzaSizeMap.get("1,2").getPizzaSizeId());
        assertEquals(1, DataLoader.pizzaSizeMap.get("1,2").getPizzaType().getTypeId());
        assertEquals(2, DataLoader.pizzaSizeMap.get("1,2").getPizzaSize().getSizeId());

        assertEquals(4, DataLoader.pizzaSizeMap.get("4,1").getPizzaTypeId());
        assertEquals(1, DataLoader.pizzaSizeMap.get("4,1").getPizzaSizeId());
        assertEquals(4, DataLoader.pizzaSizeMap.get("4,1").getPizzaType().getTypeId());
        assertEquals(1, DataLoader.pizzaSizeMap.get("4,1").getPizzaSize().getSizeId());
        
        assertNull(DataLoader.pizzaSizeMap.get("3,3"));
    }

	/**
	 * Validate the PizzaToppingMap is working correctly
	 */
    @Test
    public void _02_pizzaToppingMapTest() {
        assertEquals(1, DataLoader.pizzaToppingMap.get("1,5").getPizzaTypeId());
        assertEquals(5, DataLoader.pizzaToppingMap.get("1,5").getPizzaToppingId());
        assertEquals(1, DataLoader.pizzaToppingMap.get("1,5").getPizzaType().getTypeId());
        assertEquals(5, DataLoader.pizzaToppingMap.get("1,5").getPizzaTopping().getToppingId());
        
        assertEquals(3, DataLoader.pizzaToppingMap.get("3,2").getPizzaTypeId());
        assertEquals(2, DataLoader.pizzaToppingMap.get("3,2").getPizzaToppingId());
        assertEquals(3, DataLoader.pizzaToppingMap.get("3,2").getPizzaType().getTypeId());
        assertEquals(2, DataLoader.pizzaToppingMap.get("3,2").getPizzaTopping().getToppingId());
        
        assertNull(null, DataLoader.pizzaToppingMap.get("2,5"));
    }
    
	/**
	 *   Test the result generated from finalizedOrder
	 * 
	 *   Input :
	 *   -----------------------------------------
	 *   twoSquareLargeNeapolitanPizzaWithChickenEgg
	 *   twoRoundLargeNewYorkPizza
	 *   
	 *   Expected output :
	 *   -----------------------------------------
	 *   2 Square Large Neapolitan Pizza
	 *   2 Chicken Toppings on Neapolitan Pizza
	 *   2 Egg Toppings on Neapolitan Pizza
	 *   2 Round Large New York Pizza
	 *   
	 *   Total record : 4
	 *   Total amount : 2 * (149 + 15 + 15) + 2 * 129 = 616
	 * @throws DatabaseUnavailableException 
	 */
    @Test
    public void _03_orderPizzaSet1() throws UnauthorizedException, OrderFullfillmentException, DatabaseUnavailableException {
    	Order order = OrderTestData.generateOrderSet1();
    	Confirmation confirmation = orderService.finalizeOrder(order);

    	assertEquals(4, confirmation.getDetails().size());
       	assertEquals(616f, confirmation.getTotalAmount(), 0.001f);
    }

	/**
	 *   Test the result generated from finalizedOrder
	 * 
	 *   Input :
	 *   -----------------------------------------
	 *    OneSquareRegularCaliforniaPizzaWithBeef
	 *    twoRoundLargeNewYorkPizza
	 *    OneRoundRegularChicagoPizzaWithChickenEggSalmon
	 *    
	 *    Expected output :
	 *   --------------------------------------
	 *   1 Square Regular California Pizza
	 *   1 Beef topping on California Pizza
	 *   2 Egg Toppings on Neapolitan Pizza
	 *   1 Round Regular Chicago Pizza
	 *   1 Chicken topping on Chicago Pizza
	 *   1 Egg topping on Chicago Pizza
	 *   1 Salmon topping on Chicago Pizza
	 *   
	 *   Total record : 7
	 *   Total amount : 119 + 20 + 129 + 129 + 99 + 15 + 15 + 25 = 551
	 * @throws DatabaseUnavailableException 
	 */
    @Test
    public void _04_orderPizzaSet2() throws UnauthorizedException, OrderFullfillmentException, DatabaseUnavailableException {
    	Order order = OrderTestData.generateOrderSet2();
    	Confirmation confirmation = orderService.finalizeOrder(order);
    	
    	assertEquals(7, confirmation.getDetails().size()); 
    	assertEquals(551f, confirmation.getTotalAmount(), 0.001f);

    }
    
	/**
	 *   Test the result generated from finalizedOrder
	 * 
	 *   Input :
	 *   -----------------------------------------
	 *    oneSquareRegularChicagoPizzaWithGoatCheese
	 *    
	 *    Expected output:
	 *   --------------------------------------
	 *   Throw Exception
	 *   
	 *   No order will be processed since neither Square Regular Chicago Pizza nor Chicago Pizza with Goat Cheese Topping is not on sale
	 * @throws DatabaseUnavailableException 
	 */
    @Test(expected = OrderFullfillmentException.class)
    public void _05_orderPizzaSet3() throws UnauthorizedException, OrderFullfillmentException, DatabaseUnavailableException {
    	Order order = OrderTestData.generateOrderSet3();
    	orderService.finalizeOrder(order);

    }
    
	/**
	 *   Test the result generated from finalizedOrder
	 * 
	 *   Input :
	 *   -----------------------------------------
	 *    oneSquareRegularChicagoPizzaWithGoatCheese
	 *    
	 *    Expected output:
	 *   --------------------------------------
	 *   Throw Exception
	 *   
	 *   No order will be processed since there is no input
	 * @throws DatabaseUnavailableException 
	 */
    @Test(expected = OrderFullfillmentException.class)
    public void _06_orderPizzaNoOrder() throws UnauthorizedException, OrderFullfillmentException, DatabaseUnavailableException {
    	Order order = new Order();
    	orderService.finalizeOrder(order);

    }
}
