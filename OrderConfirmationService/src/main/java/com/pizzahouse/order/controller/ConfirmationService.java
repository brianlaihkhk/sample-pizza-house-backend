package com.pizzahouse.order.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.pizzahouse.common.database.DatabaseQuery;
import com.pizzahouse.common.exception.OrderFullfillmentException;
import com.pizzahouse.common.model.Confirmation;
import com.pizzahouse.common.model.ConfirmationDetail;
import com.pizzahouse.common.model.Response;
import com.pizzahouse.order.entity.Purchase;
import com.pizzahouse.order.entity.PurchaseDetail;

public class ConfirmationService {
	DatabaseQuery<Purchase> purchaseQuery = new DatabaseQuery<Purchase>();

	/**
	 * Process and validate confirmation object and convert to Purchase for DB persistance 
	 * @param confirmation Confirmation object that sent from PizzaService
	 * @return Confirm the order is received and persist in DB, otherwise throw Exception
	 */
	public Purchase processConfirmation(Confirmation confirmation) throws OrderFullfillmentException{
		Purchase purchase = new Purchase();
		List<PurchaseDetail> purchaseDetailList = new ArrayList<PurchaseDetail>();
		long epoch = (System.currentTimeMillis() / 1000);

		try {
			purchase.setUserId(confirmation.getUserId());
			purchase.setTotalAmount(confirmation.getTotalAmount());
			purchase.setCreationEpochTime(epoch);
			purchase.setCreationTime(new Date(epoch * 1000));
			
			for (ConfirmationDetail confirmationDetail : confirmation.getDetails()) {
				PurchaseDetail purchaseDetail = new PurchaseDetail();
				
				purchaseDetail.setPizzaTypeId(confirmationDetail.getPizzaTypeId());
				purchaseDetail.setQuantity(confirmationDetail.getQuantity());
				purchaseDetail.setSinglePrice(confirmationDetail.getSinglePrice());
				purchaseDetail.setSubItemCategoryId(confirmationDetail.getSubItemCategoryId());
				purchaseDetail.setSubItemReferenceId(confirmationDetail.getSubItemReferenceId());
				purchaseDetail.setSubTotal(confirmationDetail.getSubTotal());
				
				purchaseDetailList.add(purchaseDetail);
			}
		} catch (Exception e) {
			throw new OrderFullfillmentException("Unable to confirm order, please request support for assistance and try again later");
		}
		
		return purchase;
	}
	
	public Response<String> confirmOrder(Confirmation confirmation) throws OrderFullfillmentException{
		Response<String> response = new Response<String>();
		
		Purchase purchase = processConfirmation(confirmation);
		purchaseQuery.insert(purchase);		
		
		return response;
	}
}
