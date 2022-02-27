package com.pizzahouse.test.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pizzahouse.common.model.Confirmation;
import com.pizzahouse.common.model.ConfirmationDetail;
import com.pizzhouse.order.test.helper.ConfirmationHelper;

public class ConfirmationTestData {
	
	public static ConfirmationDetail twoSquareLargeNeapolitanPizza = ConfirmationHelper.createConfirmationDetail(1, 1, 4, 2, 149, 298);
	public static ConfirmationDetail oneSquareRegularCaliforniaPizza = ConfirmationHelper.createConfirmationDetail(4, 1, 3, 1, 119, 119);
	public static ConfirmationDetail oneRoundRegularChicagoPizza = ConfirmationHelper.createConfirmationDetail(2, 1, 1, 1, 99, 99);
	public static ConfirmationDetail twoRoundLargeNewYorkPizza = ConfirmationHelper.createConfirmationDetail(2, 1, 2, 2, 129, 258);
	public static ConfirmationDetail oneSquareRegularChicagoPizza = ConfirmationHelper.createConfirmationDetail(2, 1, 3, 1, 119, 119);

	public static ConfirmationDetail twoNeapolitanPizzaWithChicken = ConfirmationHelper.createConfirmationDetail(1, 2, 1, 2, 15, 30);
	public static ConfirmationDetail twoNeapolitanPizzaWithEgg = ConfirmationHelper.createConfirmationDetail(1, 2, 2, 2, 15, 30);
	public static ConfirmationDetail oneCaliforniaPizzaWithBeef = ConfirmationHelper.createConfirmationDetail(4, 2, 5, 1, 20, 20);
	public static ConfirmationDetail oneChicagoPizzaWithChicken = ConfirmationHelper.createConfirmationDetail(2, 2, 1, 1, 15, 15);
	public static ConfirmationDetail oneChicagoPizzaWithEgg = ConfirmationHelper.createConfirmationDetail(2, 2, 2, 1, 15, 15);
	public static ConfirmationDetail oneChicagoPizzaWithSalmon = ConfirmationHelper.createConfirmationDetail(2, 2, 4, 1, 25, 25);
	public static ConfirmationDetail oneChicagoPizzaWithGoatCheese = ConfirmationHelper.createConfirmationDetail(2, 2, 3, 1, 25, 25);

	/**
	 * Generate Confirmation Test Data Set 1
	 */
 	public static Confirmation generateConfirmationSet1(){
 		Confirmation confirmation = new Confirmation();
 		List<ConfirmationDetail> details = new ArrayList<ConfirmationDetail>();
 		details.add(twoSquareLargeNeapolitanPizza);
 		details.add(twoNeapolitanPizzaWithChicken);
 		details.add(twoNeapolitanPizzaWithEgg);
 		confirmation.setDetails(details);
 		
 		// Make sure the user id = 1 has been imported to DB
 		confirmation.setUserId(1);
 		confirmation.setTotalAmount(twoSquareLargeNeapolitanPizza.getSinglePrice() + twoNeapolitanPizzaWithChicken.getSubTotal() + twoNeapolitanPizzaWithEgg.getSubTotal());
 		return confirmation;
 	}

	/**
	 * Generate Confirmation Test Data Set 2
	 */
 	public static Confirmation generateConfirmationSet2(){
 		Confirmation confirmation = new Confirmation();
 		List<ConfirmationDetail> details = new ArrayList<ConfirmationDetail>();
 		details.add(oneSquareRegularCaliforniaPizza);
 		details.add(oneSquareRegularChicagoPizza);
 		details.add(oneChicagoPizzaWithSalmon);
 		details.add(oneChicagoPizzaWithGoatCheese);
 		confirmation.setDetails(details);

 		// Make sure the user id = 2 has been imported to DB
 		confirmation.setUserId(2);
 		confirmation.setTotalAmount(oneSquareRegularCaliforniaPizza.getSubTotal() + oneSquareRegularChicagoPizza.getSubTotal() + oneChicagoPizzaWithSalmon.getSubTotal() + oneChicagoPizzaWithGoatCheese.getSubTotal());

 		return confirmation;

 	}
}
