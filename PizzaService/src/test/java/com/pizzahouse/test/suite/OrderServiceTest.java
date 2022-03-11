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
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzahouse.common.config.Default;
import com.pizzahouse.common.exception.DatabaseUnavailableException;
import com.pizzahouse.common.exception.OrderFullfillmentException;
import com.pizzahouse.common.exception.UnauthorizedException;
import com.pizzahouse.common.model.Confirmation;
import com.pizzahouse.service.config.Connection;
import com.pizzahouse.service.controller.OrderService;
import com.pizzahouse.service.initialization.DataLoader;
import com.pizzahouse.service.model.Order;
import com.pizzahouse.test.data.DataLoaderTestData;
import com.pizzahouse.test.data.OrderTestData;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/applicationContext.xml")
@TestPropertySource(properties = {
	    "pizzaServiceHost=http://localhost:8080/PizzaService/",
	    "orderConfirmationServiceHost=http://localhost:8080/OrderConfirmationService/",
	    "serverJwtSecretKey=bcbac5b821435e0be3e59d6abdac12a94c8248288a6ebd76a56a01fbca7c0cdf",
	    "serverIssuerName=cdc43c7e9089a41897b101de70f878bcc575c839f4ad057605a3335f6a601133",
	    "jwtTtlMilliseconds=10000",
	    "orderConfirmationServiceName=confirm"
	})
public class OrderServiceTest {
	@Autowired
	protected OrderService orderService;
	@Autowired
	protected Logger logger;
	@Autowired
	protected Connection connection;
	private ObjectMapper objectMapper = new ObjectMapper();

	
	
	/**
	 * Populate data to PizzaSizeMap and PizzaToppingMap
	 */
    @BeforeClass
    public static void instantiate() {
       	Default.hibernateConfigFilename = "hibernate.dev.cfg.xml";
    	DataLoader.pizzaSizeMap = DataLoaderTestData.generatePizzaSizeMapSet1();
    	DataLoader.pizzaToppingMap = DataLoaderTestData.generatePizzaToppingMapSet1();
    }

    /**
     * 
     * initialize data
     * @throws Exception 
     */
    @Test
    public void _00_initialize() throws Exception {
    }
    
	/**
	 * Validate the PizzaSizeMap is working correctly
	 */
    @Test
    public void _01_pizzaSizeMapTest() {
        assertEquals(OrderTestData.neapolianPizzaUuid, DataLoader.pizzaSizeMap.get(OrderTestData.neapolianPizzaUuid + "," + OrderTestData.roundLargeUuid).getPizzaTypeId());
        assertEquals(OrderTestData.roundLargeUuid, DataLoader.pizzaSizeMap.get(OrderTestData.neapolianPizzaUuid + "," + OrderTestData.roundLargeUuid).getPizzaSizeId());
        assertEquals(OrderTestData.neapolianPizzaUuid, DataLoader.pizzaSizeMap.get(OrderTestData.neapolianPizzaUuid + "," + OrderTestData.squareRegularUuid).getPizzaTypeId());
        assertEquals(OrderTestData.squareRegularUuid, DataLoader.pizzaSizeMap.get(OrderTestData.neapolianPizzaUuid + "," + OrderTestData.squareRegularUuid).getPizzaSize().getSizeUuid());

        assertEquals(OrderTestData.californiaPizzaUuid, DataLoader.pizzaSizeMap.get(OrderTestData.californiaPizzaUuid + "," + OrderTestData.roundRegularUuid).getPizzaTypeId());
        assertEquals(OrderTestData.roundRegularUuid, DataLoader.pizzaSizeMap.get(OrderTestData.californiaPizzaUuid + "," + OrderTestData.roundRegularUuid).getPizzaSizeId());
        assertEquals(OrderTestData.californiaPizzaUuid, DataLoader.pizzaSizeMap.get(OrderTestData.californiaPizzaUuid + "," + OrderTestData.roundRegularUuid).getPizzaTypeId());
        assertEquals(OrderTestData.roundRegularUuid, DataLoader.pizzaSizeMap.get(OrderTestData.californiaPizzaUuid + "," + OrderTestData.roundRegularUuid).getPizzaSize().getSizeUuid());
        
        assertNull(DataLoader.pizzaSizeMap.get(OrderTestData.newYorkPizzaUuid + "," + OrderTestData.squareRegularUuid));
    }

	/**
	 * Validate the PizzaToppingMap is working correctly
	 */
    @Test
    public void _02_pizzaToppingMapTest() {
        assertEquals(OrderTestData.neapolianPizzaUuid , DataLoader.pizzaToppingMap.get(OrderTestData.neapolianPizzaUuid + "," + OrderTestData.eggUuid).getPizzaTypeId());
        assertEquals(OrderTestData.eggUuid, DataLoader.pizzaToppingMap.get(OrderTestData.neapolianPizzaUuid + "," + OrderTestData.eggUuid).getPizzaToppingId());
        assertEquals(OrderTestData.neapolianPizzaUuid , DataLoader.pizzaToppingMap.get(OrderTestData.neapolianPizzaUuid + "," + OrderTestData.beefUuid).getPizzaTypeId());
        assertEquals(OrderTestData.beefUuid, DataLoader.pizzaToppingMap.get(OrderTestData.neapolianPizzaUuid + "," + OrderTestData.beefUuid).getPizzaToppingId());
        
        assertEquals(OrderTestData.newYorkPizzaUuid, DataLoader.pizzaToppingMap.get(OrderTestData.newYorkPizzaUuid + "," + OrderTestData.chickenUuid).getPizzaTypeId());
        assertEquals(OrderTestData.chickenUuid, DataLoader.pizzaToppingMap.get(OrderTestData.newYorkPizzaUuid + "," + OrderTestData.chickenUuid).getPizzaToppingId());
        assertEquals(OrderTestData.newYorkPizzaUuid, DataLoader.pizzaToppingMap.get(OrderTestData.newYorkPizzaUuid + "," + OrderTestData.eggUuid).getPizzaTypeId());
        assertEquals(OrderTestData.eggUuid, DataLoader.pizzaToppingMap.get(OrderTestData.newYorkPizzaUuid + "," + OrderTestData.eggUuid).getPizzaToppingId());
        
        assertNull(null, DataLoader.pizzaToppingMap.get(OrderTestData.chicagoPizzaUuid + "," + OrderTestData.beefUuid));
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
	 *   2 Round Large New York Pizza
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
