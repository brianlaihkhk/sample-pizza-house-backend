package com.pizzahouse.test.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pizzahouse.common.model.Confirmation;
import com.pizzahouse.common.model.ConfirmationDetail;
import com.pizzhouse.order.test.helper.ConfirmationHelper;

public class ConfirmationTestData {
	
	public static String neapolianPizzaUuid = "e676d1c5-7920-408a-a570-cd2b3d9ed24f";
	public static String chicagePizzaUuid = "a08d319e-eba2-4d2c-bfbb-9b1e747f787e";
	public static String newYorkPizzaUuid = "3e4a0c24-b820-4a53-b379-5dacb38b439d";
	public static String californiaPizzaUuid = "3f59da26-fba9-4746-92f3-c6c003c59554";
	public static String roundRegularUuid = "0a06928c-92b9-4037-80a8-8022ca95e13b";
	public static String roundLargeUuid = "8d6ab16c-a2ab-46b3-8aa0-91674295afb7";
	public static String squareRegularUuid = "e423135c-b597-4ec9-a54a-cff57da39f38";
	public static String squareLargeUuid = "af9d2771-9dce-40a0-ac80-1adf19088017";
	public static String chickenUuid = "eca3dd33-0aab-4ce7-bb41-14bcc9212aaf";
	public static String eggUuid = "d53837f6-94d9-423c-9765-45e41073adb3";
	public static String goatCheeseUuid = "609d758d-11d0-4e9c-9643-45365a4d1817";
	public static String salmonUuid = "fb3a2777-af14-45b7-8765-a1e1d761e48e";
	public static String beefUuid = "3e85e4d5-6c0c-421c-8d52-387a8074c3ac";

	public static ConfirmationDetail twoSquareLargeNeapolitanPizza = ConfirmationHelper.createConfirmationDetail(neapolianPizzaUuid, 1, squareLargeUuid, 2, 149, 298);
	public static ConfirmationDetail oneSquareRegularCaliforniaPizza = ConfirmationHelper.createConfirmationDetail(californiaPizzaUuid,  1, squareRegularUuid, 1, 119, 119);
	public static ConfirmationDetail oneRoundRegularChicagoPizza = ConfirmationHelper.createConfirmationDetail(californiaPizzaUuid,  1, roundRegularUuid, 1, 99, 99);
	public static ConfirmationDetail twoRoundLargeNewYorkPizza = ConfirmationHelper.createConfirmationDetail(newYorkPizzaUuid, 1, roundLargeUuid, 2, 129, 258);
	public static ConfirmationDetail oneSquareRegularChicagoPizza = ConfirmationHelper.createConfirmationDetail(californiaPizzaUuid, 1, squareRegularUuid, 1, 119, 119);

	public static ConfirmationDetail twoNeapolitanPizzaWithChicken = ConfirmationHelper.createConfirmationDetail(neapolianPizzaUuid, 2, chickenUuid, 2, 15, 30);
	public static ConfirmationDetail twoNeapolitanPizzaWithEgg = ConfirmationHelper.createConfirmationDetail(neapolianPizzaUuid, 2, eggUuid, 2, 15, 30);
	public static ConfirmationDetail oneCaliforniaPizzaWithBeef = ConfirmationHelper.createConfirmationDetail(californiaPizzaUuid, 2, beefUuid, 1, 20, 20);
	public static ConfirmationDetail oneChicagoPizzaWithChicken = ConfirmationHelper.createConfirmationDetail(californiaPizzaUuid, 2, chickenUuid, 1, 15, 15);
	public static ConfirmationDetail oneChicagoPizzaWithEgg = ConfirmationHelper.createConfirmationDetail(californiaPizzaUuid, 2, eggUuid, 1, 15, 15);
	public static ConfirmationDetail oneChicagoPizzaWithSalmon = ConfirmationHelper.createConfirmationDetail(californiaPizzaUuid, 2, salmonUuid, 1, 25, 25);
	public static ConfirmationDetail oneChicagoPizzaWithGoatCheese = ConfirmationHelper.createConfirmationDetail(californiaPizzaUuid, 2, goatCheeseUuid, 1, 25, 25);

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
 		confirmation.setUserUuid("5e78a1c8-a0f3-11ec-b909-0242ac120002");
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
 		confirmation.setUserUuid("6933a05e-a0f3-11ec-b909-0242ac120002");
 		confirmation.setTotalAmount(oneSquareRegularCaliforniaPizza.getSubTotal() + oneSquareRegularChicagoPizza.getSubTotal() + oneChicagoPizzaWithSalmon.getSubTotal() + oneChicagoPizzaWithGoatCheese.getSubTotal());

 		return confirmation;

 	}
}
