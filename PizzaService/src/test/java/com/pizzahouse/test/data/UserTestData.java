package com.pizzahouse.test.data;


import com.pizzahouse.common.entity.Session;
import com.pizzahouse.common.entity.User;
import com.pizzahouse.test.helper.UserHelper;

public class UserTestData {
	
	public static long epoch = (System.currentTimeMillis() / 1000); 
	
	public static User james = UserHelper.createUser("james" + String.valueOf(epoch), "Lai" , "James", "185f8db32271fe25f561a6fc938b2e264306ec304eda518007d1764826381969");
	public static User janet = UserHelper.createUser("janet" + String.valueOf(epoch), "Chan", "Janet", "78ae647dc5544d227130a0682a51e30bc7777fbb6d8a8f17007463a3ecd1d524");
	public static User anna = UserHelper.createUser("anna" + String.valueOf(epoch), "Wong", "Anna", "f223faa96f22916294922b171a2696d868fd1f9129302eb41a45b2a2ea2ebbfd");
	public static User peter = UserHelper.createUser("peter" + String.valueOf(epoch), "Lee", "Peter", "c0e34454138b3b0b97077f1349200461517a286c44ee12f5778bb6c8b71ee9a3");
	public static Session jamesSession = null;
	public static Session janetSession = null;
	public static Session annaSession = null;
	public static Session peterSession = null;



}
