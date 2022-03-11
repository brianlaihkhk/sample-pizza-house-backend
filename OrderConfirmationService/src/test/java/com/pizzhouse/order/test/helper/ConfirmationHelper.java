package com.pizzhouse.order.test.helper;

import java.util.List;

import com.pizzahouse.common.model.Confirmation;
import com.pizzahouse.common.model.ConfirmationDetail;

public class ConfirmationHelper {

	/**
	 * Create ConfirmationDetail Object
	 * @param pizzaTypeId Pizza type (e.g California Pizza)
	 * @param subItemCategoryId Pizza realted information (Size or Topping)
	 * @param subItemReferenceId The FK of the subitem (e.g pizzaSizeId or pizzaToppingId)
	 * @param quantity Quantity of that sub item
	 * @param singlePrice Single price
	 * @param subTotal Sub total of that item (i.e quantity * singlePrice)
	 * @return ConfirmationDetail object
	 */
	public static ConfirmationDetail createConfirmationDetail(String pizzaTypeUuid, int subItemCategoryUuid, String subItemReferenceUuid, int quantity, int singlePrice, int subTotal) {
		ConfirmationDetail confirmationDetail = new ConfirmationDetail();
		confirmationDetail.setPizzaTypeUuid(pizzaTypeUuid);
		confirmationDetail.setQuantity(quantity);
		confirmationDetail.setSinglePrice(singlePrice);
		confirmationDetail.setSubItemCategoryId(subItemCategoryUuid);
		confirmationDetail.setSubItemReferenceUuid(subItemReferenceUuid);
		confirmationDetail.setSubTotal(subTotal);
		return confirmationDetail;
	}
	
	/**
	 * Create Confirmation Object
	 * @param userId User ID
	 * @param totalAmount Total amound of the purchase
	 * @param details Purchase item breakdown
	 * @return Confirmation object
	 */
	public static Confirmation createConfirmation(String userUuid, int totalAmount, List<ConfirmationDetail> details) {
		Confirmation confirmation = new Confirmation();
		confirmation.setUserUuid(userUuid);
		confirmation.setTotalAmount(totalAmount);
		confirmation.setDetails(details);
		return confirmation;
	}
}
