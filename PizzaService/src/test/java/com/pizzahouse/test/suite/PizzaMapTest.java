package com.pizzahouse.test.suite;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

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

import com.pizzahouse.common.config.Default;
import com.pizzahouse.common.exception.DatabaseUnavailableException;
import com.pizzahouse.common.exception.UserProfileException;
import com.pizzahouse.common.model.Response;
import com.pizzahouse.service.config.Connection;
import com.pizzahouse.service.controller.OrderService;
import com.pizzahouse.service.database.DatabaseQuery;
import com.pizzahouse.service.entity.Pizza;
import com.pizzahouse.service.entity.Session;
import com.pizzahouse.service.entity.User;
import com.pizzahouse.service.initialization.DataLoader;
import com.pizzahouse.service.model.FlattenPizzaSize;
import com.pizzahouse.service.model.FlattenPizzaTopping;
import com.pizzahouse.test.data.UserTestData;

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
public class PizzaMapTest {
	@Autowired
	protected Logger logger;
	@Autowired	
	protected DatabaseQuery<User> userQuery;
	@Autowired
	protected DataLoader dataLoader;
	@Autowired
	protected Connection connection;
	
    @BeforeClass
    public static void instantiate() {
       	Default.hibernateConfigFilename = "hibernate.dev.cfg.xml";
    }
	
	/**
	 * Validate DB connection has been established before UserService Test
	 * @throws Exception 
	 */
    @Test
    public void _00_initialize() throws Exception {
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
