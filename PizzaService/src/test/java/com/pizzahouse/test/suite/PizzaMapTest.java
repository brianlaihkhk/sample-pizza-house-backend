package com.pizzahouse.test.suite;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pizzahouse.common.exception.DatabaseUnavailableException;
import com.pizzahouse.common.exception.UserProfileException;
import com.pizzahouse.common.model.Response;
import com.pizzahouse.service.controller.OrderService;
import com.pizzahouse.service.database.DatabaseQuery;
import com.pizzahouse.service.entity.Pizza;
import com.pizzahouse.service.entity.Session;
import com.pizzahouse.service.entity.User;
import com.pizzahouse.service.initialization.DataLoader;
import com.pizzahouse.service.initialization.PropertiesLoader;
import com.pizzahouse.service.model.FlattenPizzaSize;
import com.pizzahouse.service.model.FlattenPizzaTopping;
import com.pizzahouse.test.data.UserTestData;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/applicationContext.xml")
public class PizzaMapTest {
	@Autowired
	protected Logger logger;
	@Autowired	
	protected DatabaseQuery<User> userQuery;
	@Autowired
	protected DataLoader dataLoader;
	@Autowired
	protected PropertiesLoader propertiesLoader;
	
	/**
	 * Validate DB connection has been established before UserService Test
	 * @throws Exception 
	 */
    @Test
    public void _00_initialize() throws Exception {
    	propertiesLoader.setConnectionInputStream(this.getClass().getClassLoader().getResourceAsStream("connection.properties"));
    	propertiesLoader.setDefaultInputStream(this.getClass().getClassLoader().getResourceAsStream("default.properties"));
    	propertiesLoader.populate();
    	assertEquals(true, userQuery.checkConnection());
    }
    
	/**
	 * Create pizzaMapTest
	 * @throws UserProfileException 
	 * @throws NoSuchAlgorithmException 
	 * @throws DatabaseUnavailableException 
	 */
    @Test
    public void _01_createPizzaMapTest() throws NoSuchAlgorithmException, UserProfileException, DatabaseUnavailableException {
    	DataLoader.pizzaSizeMap = new HashMap<String, FlattenPizzaSize>();
    	DataLoader.pizzaToppingMap = new HashMap<String, FlattenPizzaTopping>();

    	assertEquals(0, DataLoader.pizzaSizeMap.size());
    	assertEquals(0, DataLoader.pizzaToppingMap.size());
    	
    	dataLoader.run(null);
    	
    	
    	assertEquals(true, DataLoader.pizzaSizeMap.size() > 0);
    	assertEquals(true, DataLoader.pizzaToppingMap.size() > 0);

    }
    
}
