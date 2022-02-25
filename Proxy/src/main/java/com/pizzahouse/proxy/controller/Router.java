package com.pizzahouse.proxy.controller;

import java.security.NoSuchAlgorithmException;

import javax.persistence.RollbackException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pizzahouse.common.entity.Session;
import com.pizzahouse.common.model.ErrorDetail;
import com.pizzahouse.common.model.Order;
import com.pizzahouse.common.model.Response;
import com.pizzahouse.common.security.SecurityService;
import com.pizzhouse.common.exception.UnauthorizedException;

public class Router {
	
	private UserService userService;
	private OrderService orderService;
	private SecurityService securityService;

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
	
	@RequestMapping(value = "/order", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Response<String> submitOrder (int id, String token, Order order) {
		Response<String> response = new Response<String>();
		ErrorDetail error = new ErrorDetail();
		
		try {
			securityService.checkUserTokenByUserId(id, token);
			response = orderService.submitOrder(id, token, order);
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
		} catch(Exception e) {
			error.setErrorCode(5001);
			error.setErrorMessage("Unknown error occured, please try again later");
			
			response.setSuccess(false);
			response.setError(error);
		}
		return response;
	}

}
