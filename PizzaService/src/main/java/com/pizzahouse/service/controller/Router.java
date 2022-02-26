package com.pizzahouse.service.controller;

import java.security.NoSuchAlgorithmException;

import javax.persistence.RollbackException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pizzahouse.common.security.SecurityService;
import com.pizzahouse.common.model.ErrorDetail;
import com.pizzahouse.service.model.Order;
import com.pizzahouse.common.model.Response;
import com.pizzahouse.common.entity.Session;
import com.pizzahouse.common.exception.OrderFullfillmentException;
import com.pizzahouse.common.exception.UnauthorizedException;
import com.pizzahouse.common.exception.UserProfileException;

public class Router {
	
	private UserService userService;
	private OrderService orderService;
	private SecurityService securityService;

	/**
	 * User login (via username) WS end point 
	 * @param username Username of the user
	 * @param password Password in SHA256 salt format
	 * @return Success with token returned / fail with error message
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Response<Session> getSessionByUsername (String username, String password) {
		Response<Session> response = new Response<Session>();
		ErrorDetail error = new ErrorDetail();
		
		try {
			response = userService.getSessionByUsername(username, password);
		} catch(RollbackException e) {
			error.setErrorCode(4060);
			error.setErrorMessage("Database error, transaction has been rejected and rolled back");
			
			response.setSuccess(false);
			response.setError(error);
		} catch(Exception e) {
			error.setErrorCode(5001);
			error.setErrorMessage("Unknown error occured, please try again later");
			
			response.setSuccess(false);
			response.setError(error);
		}
		return response;
	}

	/**
	 * User registration WS end point 
	 * @param id User id of the user
	 * @param token Session token obtained from login
	 * @param order Data searialized 
	 * @return Success with order confirmation / fail with error message
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Response<Session> createUser (String username, String firstName, String lastName, String password) {
		Response<Session> response = new Response<Session>();
		ErrorDetail error = new ErrorDetail();
		
		try {
			response = userService.createUser(username, firstName, lastName, password);
		} catch(RollbackException e) {
			error.setErrorCode(4060);
			error.setErrorMessage("Database error, transaction has been rejected and rolled back");
			
			response.setSuccess(false);
			response.setError(error);
		} catch(UserProfileException e) {
			error.setErrorCode(4500);
			error.setErrorMessage(e.getMessage());
			
			response.setSuccess(false);
			response.setError(error);
		} catch(NoSuchAlgorithmException e) {
			error.setErrorCode(4060);
			error.setErrorMessage("Database error, transaction has been rejected and rolled back");
			
			response.setSuccess(false);
			response.setError(error);
		} catch(Exception e) {
			error.setErrorCode(5001);
			error.setErrorMessage("Unknown error occured, please try again later");
			
			response.setSuccess(false);
			response.setError(error);
		}
		return response;
		
	}

	/**
	 * Submit order WS end point - For user submit the order request after selected the pizza he/she wants to buy
	 * @param id User id of the user
	 * @param token Session token obtained from login / registration
	 * @param order Order submitted by user, serialized to JAVA POJO for further processing 
	 * @return Success with order confirmation / fail with error message
	 */
	@RequestMapping(value = "/order", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Response<String> submitOrder (int id, String token, Order order) {
		Response<String> response = new Response<String>();
		ErrorDetail error = new ErrorDetail();
		
		try {
			securityService.checkUserTokenByUserId(id, token);
			response = orderService.submitOrder(id, order);
		} catch(RollbackException e) {
			error.setErrorCode(4060);
			error.setErrorMessage("Database error, transaction has been rejected and rolled back");
			
			response.setSuccess(false);
			response.setError(error);
		} catch(UnauthorizedException e) {
			error.setErrorCode(4011);
			error.setErrorMessage(e.getMessage());
			
			response.setSuccess(false);
			response.setError(error);
		} catch(OrderFullfillmentException e) {
			error.setErrorCode(4400);
			error.setErrorMessage(e.getMessage());
			
			response.setSuccess(false);
			response.setError(error);
		} catch(Exception e) {
			error.setErrorCode(5001);
			error.setErrorMessage("Unknown error occured, please try again later");
			
			response.setSuccess(false);
			response.setError(error);
		}
		return response;
	}

}
