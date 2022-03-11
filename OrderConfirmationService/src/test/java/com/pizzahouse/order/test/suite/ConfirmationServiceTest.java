package com.pizzahouse.order.test.suite;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pizzahouse.common.config.Default;
import com.pizzahouse.common.exception.OrderFullfillmentException;
import com.pizzahouse.common.model.Confirmation;
import com.pizzahouse.common.model.ConfirmationDetail;
import com.pizzahouse.common.model.Response;
import com.pizzahouse.order.config.Connection;
import com.pizzahouse.order.controller.ConfirmationService;
import com.pizzahouse.order.database.DatabaseQuery;
import com.pizzahouse.order.entity.Purchase;
import com.pizzahouse.order.entity.PurchaseDetail;
import com.pizzahouse.test.data.ConfirmationTestData;
import com.pizzahouse.test.data.ConnectionConfig;

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
public class ConfirmationServiceTest {
	@Autowired
	protected DatabaseQuery<Purchase> purchaseQuery;
	@Autowired
	protected ConfirmationService confirmationService;
	@Autowired
	protected Logger logger;
	@Autowired
	protected Connection connection;
	
    @BeforeClass
    public static void instantiate() {
    	Default.hibernateConfigFilename = "hibernate.dev.cfg.xml";
    }

	/**
	 * Validate DB connection has been established before ConfirmationService Test
	 */
    @Test
    public void _00_initialize() throws Exception {
    	assertEquals(true, purchaseQuery.checkConnection());
    }
    
	/**
	 * Validate DB connection has been established before ConfirmationService Test
	 * @throws OrderFullfillmentException 
	 */
    @Test
    public void _01_processConfirmationSet1() throws OrderFullfillmentException {
    	float purchaseSubItemSum = 0.0f;
    	float confirmationSubItemSum = 0.0f;

    	Confirmation confirmation = ConfirmationTestData.generateConfirmationSet1();
    	Purchase purchase = confirmationService.processConfirmation(confirmation);
    	List<PurchaseDetail> purchaseDetails = confirmationService.processConfirmationDetail("e1f1c360-0816-43ca-a171-e016c817588d", confirmation);

    	assertEquals(purchase.getUserUuid(), confirmation.getUserUuid());
    	assertEquals(purchase.getTotalAmount(), confirmation.getTotalAmount(), 0.001f);
    	assertEquals(purchaseDetails.size(), confirmation.getDetails().size());

    	for (PurchaseDetail purchaseDetail : purchaseDetails) {
    		purchaseSubItemSum += purchaseDetail.getSubTotal();
    	}
    	for (ConfirmationDetail confirmationDetail : confirmation.getDetails()) {
    		confirmationSubItemSum += confirmationDetail.getSubTotal();
    	}
    	assertEquals(purchaseSubItemSum, confirmationSubItemSum, 0.001f);

    }
	
	/**
	 * Validate DB connection has been established before ConfirmationService Test
	 * @throws OrderFullfillmentException 
	 */
    @Test
    public void _02_processConfirmationSet2() throws OrderFullfillmentException {
    	float purchaseSubItemSum = 0.0f;
    	float confirmationSubItemSum = 0.0f;

    	Confirmation confirmation = ConfirmationTestData.generateConfirmationSet2();
    	Purchase purchase = confirmationService.processConfirmation(confirmation);
    	List<PurchaseDetail> purchaseDetails = confirmationService.processConfirmationDetail("fa6d7ea5-d0c3-4e95-8d73-7c95ca439332", confirmation);

    	assertEquals(purchase.getUserUuid(), confirmation.getUserUuid());
    	assertEquals(purchase.getTotalAmount(), confirmation.getTotalAmount(), 0.001f);
    	assertEquals(purchaseDetails.size(), confirmation.getDetails().size());

    	for (PurchaseDetail purchaseDetail : purchaseDetails) {
    		purchaseSubItemSum += purchaseDetail.getSubTotal();
    	}
    	for (ConfirmationDetail confirmationDetail : confirmation.getDetails()) {
    		confirmationSubItemSum += confirmationDetail.getSubTotal();
    	}
    	assertEquals(purchaseSubItemSum, confirmationSubItemSum, 0.001f);
    }
    
    
	/**
	 * Validate DB connection has been established before ConfirmationService Test
	 * @throws OrderFullfillmentException 
	 */
    @Test
    public void _03_submitConfirmationSet1() throws OrderFullfillmentException {
    	Confirmation confirmation = ConfirmationTestData.generateConfirmationSet1();
    	Response<String> response = confirmationService.confirmOrder(confirmation);
    	assertEquals(true, response.isSuccess());
    }
	
	/**
	 * Validate DB connection has been established before ConfirmationService Test
	 * @throws OrderFullfillmentException 
	 */
    @Test
    public void _04_submitConfirmationSet2() throws OrderFullfillmentException {
    	Confirmation confirmation = ConfirmationTestData.generateConfirmationSet2();
    	Response<String> response = confirmationService.confirmOrder(confirmation);
    	assertEquals(true, response.isSuccess());
    }
}
