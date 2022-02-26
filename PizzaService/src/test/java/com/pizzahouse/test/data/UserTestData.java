package com.pizzahouse.test.data;


import com.pizzahouse.common.entity.User;
import com.pizzahouse.test.helper.UserHelper;

public class UserTestData {
	
	public long epoch = (System.currentTimeMillis() / 1000); 
	
	public User james = UserHelper.createUser(1, "james", "Lai" , "James", "185f8db32271fe25f561a6fc938b2e264306ec304eda518007d1764826381969", null);
	public User janet = UserHelper.createUser(2, "janet", "Chan", "Janet", "78ae647dc5544d227130a0682a51e30bc7777fbb6d8a8f17007463a3ecd1d524", null);
	public User anna = UserHelper.createUser(3, "anna", "Wong", "Anna", "f223faa96f22916294922b171a2696d868fd1f9129302eb41a45b2a2ea2ebbfd", null);
	public User peter = UserHelper.createUser(4, "peter", "Lee", "Peter", "c0e34454138b3b0b97077f1349200461517a286c44ee12f5778bb6c8b71ee9a3", null);


}
