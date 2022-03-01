package com.pizzahouse.order.controller;

import javax.persistence.RollbackException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzahouse.common.config.Connection;
import com.pizzahouse.common.config.ErrorCode;
import com.pizzahouse.common.exception.JwtIssuerNotMatchException;
import com.pizzahouse.common.exception.JwtMessageExpiredException;
import com.pizzahouse.common.exception.OrderFullfillmentException;
import com.pizzahouse.common.model.Confirmation;
import com.pizzahouse.common.model.ErrorDetail;
import com.pizzahouse.common.model.Response;
import com.pizzahouse.order.controller.ConfirmationService;
import com.pizzahouse.order.security.JwtService;;

@SpringBootApplication
@Controller
@ComponentScan(basePackages = "com.pizzahouse.order")
@RequestMapping("/")
public class Router extends SpringBootServletInitializer {
	private ObjectMapper mapper = new ObjectMapper();
	@Autowired
	protected org.slf4j.Logger logger;
	@Autowired
	protected ConfirmationService confirmationService;
	@Autowired
	protected JwtService<Confirmation> jwtService;
	
	/**
	 * Confirmation endpoint - Receive PizzaService request, communication through secure channel
	 * @param confirmation Order confrmation request from PizzaService
	 * @return Success with order confirmation / fail with error message
	 */
	@RequestMapping(value = "confirm", method = RequestMethod.POST, produces = "text/plain")
	@ResponseBody
	public String submitOrder (@RequestBody String jwtMessage) {
		Response<String> response = new Response<String>();
		ErrorDetail error = new ErrorDetail();
		Confirmation confirmation = new Confirmation();
		String jsonResponse = "";
		
		try {
			logger.info("calling OrderConfirmation /confirm endpoint : " + jwtMessage);

			confirmation = jwtService.decodeMessage(Confirmation.class, jwtMessage);
			logger.info("decode jwt message : " + mapper.writeValueAsString(confirmation));

			response = confirmationService.confirmOrder(confirmation);
			
			logger.info("Finish calling OrderConfirmation /confirm endpoint, wrapping using Jwt : " + mapper.writeValueAsString(response));
		} catch(JsonMappingException e) {
			error.setErrorCode(ErrorCode.jsonMappingException);
			error.setErrorMessage(e.getMessage());
			
			response.setSuccess(false);
			response.setError(error);
			logger.error("[JsonMappingException] Json Mapping error during serialization : " + e.getMessage());

		} catch (JsonProcessingException e) {
			error.setErrorCode(ErrorCode.jsonProcessingException);
			error.setErrorMessage("Server error on converting requested object");
			
			response.setSuccess(false);
			response.setError(error);
			logger.error("[JsonProcessingException] Json Processing error : " + e.getMessage());
		} catch(RollbackException e) {
			error.setErrorCode(ErrorCode.rollbackException);
			
			response.setSuccess(false);
			response.setError(error);
			error.setErrorMessage("[RollbackException] Database error, transaction has been rejected and rolled back" + e.getMessage());
		} catch(OrderFullfillmentException e) {
			error.setErrorCode(ErrorCode.orderFullfillmentException);
			error.setErrorMessage(e.getMessage());
			
			response.setSuccess(false);
			response.setError(error);
			logger.warn("[OrderFullfillmentException] Processing error during order fullfillment : " + e.getMessage());

		} catch(JwtMessageExpiredException e) {
			error.setErrorCode(ErrorCode.jwtMessageExpiredException);
			error.setErrorMessage(e.getMessage());
			
			response.setSuccess(false);
			response.setError(error);
			logger.warn("[JwtMessageExpiredException] Jwt message has expired : " + e.getMessage());

		} catch(JwtIssuerNotMatchException e) {
			error.setErrorCode(ErrorCode.jwtIssuerNotMatchException);
			error.setErrorMessage(e.getMessage());
			
			response.setSuccess(false);
			response.setError(error);
			logger.error("[JwtIssuerNotMatchException] Jwt issuer does not match : " + e.getMessage());

		} catch(Exception e) {
			error.setErrorCode(ErrorCode.baseException);
			error.setErrorMessage("Unknown error occured, please try again later");
			
			response.setSuccess(false);
			response.setError(error);
			logger.error("[Exception] Unknown error occured : " + e.getMessage());
			e.printStackTrace();
		}
		
		try {
			jsonResponse = mapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			error.setErrorCode(ErrorCode.jsonProcessingException);
			error.setErrorMessage("[JsonProcessingException] Json Processing Exception occured : " + e.getMessage());
			
			response.setSuccess(false);
			response.setError(error);
			logger.error("[JsonProcessingException] Json Processing Exception occured : " + e.getMessage());
			e.printStackTrace();
		} catch(Exception e) {
			error.setErrorCode(ErrorCode.baseException);
			error.setErrorMessage("Unknown error occured, please try again later");
			
			response.setSuccess(false);
			response.setError(error);
			logger.error("[Exception] Unknown error occured : " + e.getMessage());
			e.printStackTrace();
		}
		
		String jwtResponseMessage = jwtService.createJwt(String.valueOf(confirmation.getUserId()), Connection.serverIssuerName, jsonResponse, Connection.jwtTtlMilliseconds, Connection.serverJwtSecretKey);
		logger.info("Finish calling OrderConfirmation /confirm endpoint : ");

		return jwtResponseMessage;
	}

    public static void main(String[] args) {
    	System.out.println("WebConfiguration run init");
    }

}
