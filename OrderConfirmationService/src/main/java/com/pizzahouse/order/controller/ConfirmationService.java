package com.pizzahouse.order.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzahouse.common.exception.OrderFullfillmentException;
import com.pizzahouse.common.model.Confirmation;
import com.pizzahouse.common.model.ConfirmationDetail;
import com.pizzahouse.common.model.Response;
import com.pizzahouse.order.database.DatabaseQuery;
import com.pizzahouse.order.entity.Purchase;
import com.pizzahouse.order.entity.PurchaseDetail;

@Service
public class ConfirmationService {
	@Autowired
	protected DatabaseQuery<Purchase> purchaseQuery;
	@Autowired
	protected DatabaseQuery<PurchaseDetail> purchaseDetailQuery;
	@Autowired
	protected Logger logger;
	private ObjectMapper mapper = new ObjectMapper();
	
	
	/**
	 * Process and validate confirmation object and convert to Purchase for DB persistance 
	 * @param confirmation Confirmation object that sent from PizzaService
	 * @return Purchase object that persist to DB
	 */
	public Purchase processConfirmation(Confirmation confirmation) throws OrderFullfillmentException{
		Purchase purchase = new Purchase();

		long epoch = (System.currentTimeMillis() / 1000);

		try {
			purchase.setUserId(confirmation.getUserId());
			purchase.setTotalAmount(confirmation.getTotalAmount());
			purchase.setCreationEpochTime(epoch);
			purchase.setCreationTime(new Date(epoch * 1000));
			
			
		} catch (Exception e) {
			throw new OrderFullfillmentException("Unable to confirm order, please request support for assistance and try again later");
		}
		
		return purchase;
	}

	/**
	 * Process and validate confirmation object and convert to PurchaseDetail for DB persistance 
	 * @param confirmation Confirmation object that sent from PizzaService
	 * @return List of PurchaseDetail object that persist to DB
	 */
	public List<PurchaseDetail> processConfirmationDetail(int purchaseId, Confirmation confirmation) throws OrderFullfillmentException{
		List<PurchaseDetail> details = new ArrayList<PurchaseDetail>();

		try {
		
			for (ConfirmationDetail confirmationDetail : confirmation.getDetails()) {
				PurchaseDetail purchaseDetail = new PurchaseDetail();
				
				purchaseDetail.setPurchaseId(purchaseId);
				purchaseDetail.setPizzaTypeId(confirmationDetail.getPizzaTypeId());
				purchaseDetail.setQuantity(confirmationDetail.getQuantity());
				purchaseDetail.setSinglePrice(confirmationDetail.getSinglePrice());
				purchaseDetail.setSubItemCategoryId(confirmationDetail.getSubItemCategoryId());
				purchaseDetail.setSubItemReferenceId(confirmationDetail.getSubItemReferenceId());
				purchaseDetail.setSubTotal(confirmationDetail.getSubTotal());
				
				details.add(purchaseDetail);
			}
			
		} catch (Exception e) {
			throw new OrderFullfillmentException("Unable to confirm order, please request support for assistance and try again later");
		}
		
		return details;
	}
	
	
	/**
	 * Process and validate confirmation object and convert to Purchase for DB persistance 
	 * @param confirmation Confirmation object that sent from PizzaService
	 * @return Purchase object that persist to DB
	 */
	public Response<String> confirmOrder(Confirmation confirmation) throws OrderFullfillmentException{
		Response<String> response = new Response<String>();
		try {
			System.out.println(mapper.writeValueAsString(confirmation));
			Purchase purchase = processConfirmation(confirmation);
			System.out.println(mapper.writeValueAsString(purchase));
			int recordId = purchaseQuery.insert(purchase);	
			
			logger.info ("Record id : " + String.valueOf(recordId));
			
			if (recordId > 0) {
				purchaseDetailQuery.insertList(processConfirmationDetail(recordId, confirmation));
			} else {
				throw new OrderFullfillmentException("Unable to insert into DB, transaction rolled back");
			}
			response.setSuccess(true);
			response.setPayload("Order cofirmed : " + recordId);
		} catch (JsonProcessingException e) {
			throw new OrderFullfillmentException("Unable to insert into DB, transaction rolled back : " + e.getMessage());
		}

		
		return response;
	}
//	/**
//	 * Process and validate confirmation object and convert to Purchase for DB persistance 
//	 * @param confirmation Confirmation object that sent from PizzaService
//	 * @return Confirm the order is received and persist in DB, otherwise throw Exception
//	 */
//	public Purchase processConfirmation(Confirmation confirmation) throws OrderFullfillmentException{
//		Purchase purchase = new Purchase();
//		List<PurchaseDetail> details = new ArrayList<PurchaseDetail>();
//		long epoch = (System.currentTimeMillis() / 1000);
//
//		try {
//			purchase.setUserId(confirmation.getUserId());
//			purchase.setTotalAmount(confirmation.getTotalAmount());
//			purchase.setCreationEpochTime(epoch);
//			purchase.setCreationTime(new Date(epoch * 1000));
//			
//			for (ConfirmationDetail confirmationDetail : confirmation.getDetails()) {
//				PurchaseDetail purchaseDetail = new PurchaseDetail();
//				
//				purchaseDetail.setPizzaTypeId(confirmationDetail.getPizzaTypeId());
//				purchaseDetail.setQuantity(confirmationDetail.getQuantity());
//				purchaseDetail.setSinglePrice(confirmationDetail.getSinglePrice());
//				purchaseDetail.setSubItemCategoryId(confirmationDetail.getSubItemCategoryId());
//				purchaseDetail.setSubItemReferenceId(confirmationDetail.getSubItemReferenceId());
//				purchaseDetail.setSubTotal(confirmationDetail.getSubTotal());
//				
//				details.add(purchaseDetail);
//			}
//			
//			purchase.setDetails(details);
//		} catch (Exception e) {
//			throw new OrderFullfillmentException("Unable to confirm order, please request support for assistance and try again later");
//		}
//		
//		return purchase;
//	}
	
//	/**
//	 * Process and validate confirmation object and convert to Purchase for DB persistance 
//	 * @param confirmation Confirmation object that sent from PizzaService
//	 * @return Purchase object that persist to DB
//	 */
//	public Response<String> confirmOrder(Confirmation confirmation) throws OrderFullfillmentException{
//		Response<String> response = new Response<String>();
//		
//		Purchase purchase = processConfirmation(confirmation);
//		int recordId = purchaseQuery.insert(purchase);	
//		
//		response.setSuccess(true);
//		response.setPayload("Order cofirmed : " + recordId);
//		
//		return response;
//	}

}
