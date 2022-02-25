package com.pizzahouse.proxy.controller;

import com.pizzahouse.common.model.ErrorDetail;
import com.pizzahouse.common.model.Order;
import com.pizzahouse.common.model.Response;
import com.pizzahouse.common.security.SecurityService;
import com.pizzahouse.common.entity.Session;
import com.pizzahouse.common.entity.User;
import com.pizzahouse.common.database.DatabaseTransaction;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
	DatabaseTransaction<User> dbTransaction = new DatabaseTransaction<User>();
	SecurityService securityService = new SecurityService();

	// Login function
	// Input : username , password (SHA256 with salt from frontend)
	// Output : Success with session token / fail with error message
	public Response<Session> getSessionByUsername (String username, String password) {
		Response<Session> response = new Response<Session>();
		
		Map<String, Object> queryList = new HashMap<String, Object>();
		queryList.put("username", username);
		
		List<User> result = dbTransaction.selectByParameter("findUserByUsername", queryList);
				
		if (result.size() == 1 && result.get(0).getPassword() == password) {
			response.setSuccess(true);
			response.setPayload(result.get(0).getSession());
			return response;
		} else {
			ErrorDetail error = new ErrorDetail();
			error.setErrorCode(4030);
			error.setErrorMessage("Cannot obtain user by username");
			
			response.setSuccess(false);
			response.setError(error);
		}
		
		return response;

	}

	// Create new user for later use
	// Input : username, firstname, lastname , SHA256 with salt password
	// Output : Success with session token / fail with error message
	public Response<Session> createUser (String username, String firstName, String lastName, String password) throws NoSuchAlgorithmException {
		Response<Session> response = new Response<Session>();
			
		User user = new User (username, firstName, lastName, password);
		user = securityService.setToken(user);
		int id = dbTransaction.insert(user);

		if (id > 0) {
			response.setSuccess(true);
			response.setPayload(dbTransaction.select(user, id).getSession());
		} else {
			ErrorDetail error = new ErrorDetail();
			error.setErrorCode(4031);
			error.setErrorMessage("Unable to create new user");
			
			response.setSuccess(false);
			response.setError(error);
		}
		
		return response;
	}

}
