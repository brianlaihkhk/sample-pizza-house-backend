package com.pizzahouse.proxy.controller;

import com.pizzahouse.proxy.model.Response;
import com.pizzahouse.proxy.entity.Session;

public class UserService {

	// Input : username , password (SHA256 from frontend)
	// Output : result (Success with session token / fail with error message)
	
	public Response<Session> userLogin (String username, String passwordHash) {
		
		
	}
}
