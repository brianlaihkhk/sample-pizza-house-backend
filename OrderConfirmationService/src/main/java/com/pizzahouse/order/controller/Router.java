package com.pizzahouse.order.controller;

import javax.persistence.RollbackException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzahouse.common.config.ErrorCode;
import com.pizzahouse.common.exception.OrderFullfillmentException;
import com.pizzahouse.common.model.Confirmation;
import com.pizzahouse.common.model.ErrorDetail;
import com.pizzahouse.common.model.Response;

import com.pizzahouse.order.controller.ConfirmationService;;

@Controller
@RequestMapping("/")
public class Router {
	ConfirmationService confirmationService = new ConfirmationService();
	private ObjectMapper mapper = new ObjectMapper();
	@Autowired
	protected Logger logger;
	
	/**
	 * Confirmation endpoint - Receive PizzaService request, communication through secure channel
	 * @param confirmation Order confrmation request from PizzaService
	 * @return Success with order confirmation / fail with error message
	 */
	@RequestMapping(value = "confirm", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Response<String> submitOrder (Confirmation confirmation) {
		Response<String> response = new Response<String>();
		ErrorDetail error = new ErrorDetail();
		
		try {
			logger.info("calling OrderConfirmation /confirm endpoint : " + mapper.writeValueAsString(confirmation));
			response = confirmationService.confirmOrder(confirmation);
			logger.info("Finish calling OrderConfirmation /confirm endpoint : " + mapper.writeValueAsString(response));
		} catch (JsonProcessingException e) {
			error.setErrorCode(ErrorCode.jsonProcessingException);
			error.setErrorMessage("Server error on converting requested object");
			
			response.setSuccess(false);
			response.setError(error);
		} catch(RollbackException e) {
			error.setErrorCode(ErrorCode.rollbackException);
			error.setErrorMessage("Database error, transaction has been rejected and rolled back");
			
			response.setSuccess(false);
			response.setError(error);
		} catch(OrderFullfillmentException e) {
			error.setErrorCode(ErrorCode.orderFullfillmentException);
			error.setErrorMessage(e.getMessage());
			
			response.setSuccess(false);
			response.setError(error);
		} catch(Exception e) {
			error.setErrorCode(ErrorCode.baseException);
			error.setErrorMessage("Unknown error occured, please try again later");
			
			response.setSuccess(false);
			response.setError(error);
		}
		
		return response;
	}


}
