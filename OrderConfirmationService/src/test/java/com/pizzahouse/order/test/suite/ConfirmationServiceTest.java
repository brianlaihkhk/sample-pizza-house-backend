package com.pizzahouse.order.test.suite;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pizzahouse.common.exception.OrderFullfillmentException;
import com.pizzahouse.common.model.Confirmation;
import com.pizzahouse.common.model.ConfirmationDetail;
import com.pizzahouse.common.model.Response;
import com.pizzahouse.order.controller.ConfirmationService;
import com.pizzahouse.order.database.DatabaseQuery;
import com.pizzahouse.order.entity.Purchase;
import com.pizzahouse.order.entity.PurchaseDetail;
import com.pizzahouse.order.initialization.PropertiesLoader;
import com.pizzahouse.test.data.ConfirmationTestData;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/applicationContext.xml")
public class ConfirmationServiceTest {
	@Autowired
	protected DatabaseQuery<Purchase> purchaseQuery;
	@Autowired
	protected ConfirmationService confirmationService;
	@Autowired
	protected Logger logger;
	@Autowired
	protected PropertiesLoader propertiesLoader;
	
	/**
	 * Validate DB connection has been established before ConfirmationService Test
	 */
    @Test
    public void _00_initialize() throws Exception {
    	propertiesLoader.setConnectionInputStream(this.getClass().getClassLoader().getResourceAsStream("connection.properties"));
    	propertiesLoader.setDefaultInputStream(this.getClass().getClassLoader().getResourceAsStream("default.properties"));
    	propertiesLoader.populate();
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
    	List<PurchaseDetail> purchaseDetails = confirmationService.processConfirmationDetail(1, confirmation);

    	assertEquals(purchase.getUserId(), confirmation.getUserId());
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
    	List<PurchaseDetail> purchaseDetails = confirmationService.processConfirmationDetail(2, confirmation);

    	assertEquals(purchase.getUserId(), confirmation.getUserId());
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
