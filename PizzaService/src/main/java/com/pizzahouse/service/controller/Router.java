package com.pizzahouse.service.controller;

import java.security.NoSuchAlgorithmException;

import javax.persistence.RollbackException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.pizzahouse.common.model.ErrorDetail;
import com.pizzahouse.service.entity.Session;
import com.pizzahouse.service.model.Order;
import com.pizzahouse.service.model.RequestUserCreate;
import com.pizzahouse.service.model.RequestUserLogin;
import com.pizzahouse.service.security.SecurityService;
import com.pizzahouse.common.model.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.pizzahouse.common.config.Default;
import com.pizzahouse.common.config.ErrorCode;
import com.pizzahouse.common.exception.DatabaseUnavailableException;
import com.pizzahouse.common.exception.OrderFullfillmentException;
import com.pizzahouse.common.exception.UnauthorizedException;
import com.pizzahouse.common.exception.UserProfileException;

@SpringBootApplication
@Controller
@ComponentScan(basePackages = "com.pizzahouse.service")
@RequestMapping("/")
public class Router extends SpringBootServletInitializer {
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	protected org.slf4j.Logger logger;
	@Autowired
	protected org.hibernate.Session dbSession;
	@Autowired
	SecurityService securityService;
	@Autowired
	UserService userService;
	@Autowired
	OrderService orderService;
	
	/**
	 * User login (via username) WS end point 
	 * @param username Username of the user
	 * @param password Password in SHA256 salt format
	 * @return Success with token returned / fail with error message
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Response<Session> getSessionByUsername (@RequestBody RequestUserLogin requestUserLogin) {
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		Response<Session> response = new Response<Session>();
		ErrorDetail error = new ErrorDetail();
		
		try {
			logger.info("calling PizzaHouse /login endpoint : " + requestUserLogin.getUsername());
			if (requestUserLogin.getUsername() == null || requestUserLogin.getPassword() == null) {
				throw new UserProfileException("Required parameter cannot be empty");
			}
			
			response = userService.userLogin(requestUserLogin.getUsername(), requestUserLogin.getPassword());
			logger.info("Finish calling PizzaHouse /login endpoint : " + mapper.writeValueAsString(response));
		} catch(RollbackException e) {
			error.setErrorCode(ErrorCode.rollbackException);
			error.setErrorMessage("Database error, transaction has been rejected and rolled back");
			
			response.setSuccess(false);
			response.setError(error);
			logger.warn("[RollbackException] transaction has been rejected and rolled back" + e.getMessage());
		} catch(DatabaseUnavailableException e) {
			error.setErrorCode(ErrorCode.databaseUnavailableException);
			error.setErrorMessage("Database connection error, cannot obtain DB session");
			
			response.setSuccess(false);
			response.setError(error);
			logger.error("[DatabaseUnavailableException] Database connection error, cannot obtain DB session" + e.getMessage());
		} catch(UserProfileException e) {
			error.setErrorCode(ErrorCode.userProfileException);
			error.setErrorMessage(e.getMessage());
			
			response.setSuccess(false);
			response.setError(error);
			logger.warn("[UserProfileException] User Profile handling error : " + e.getMessage());
		} catch(NoSuchAlgorithmException e) {
			error.setErrorCode(ErrorCode.noSuchAlgorithmException);
			error.setErrorMessage("Database error, transaction has been rejected and rolled back");
			
			response.setSuccess(false);
			response.setError(error);
			logger.warn("[NoSuchAlgorithmException] Database error, transaction has been rejected and rolled back : " + e.getMessage());
		} catch(UnauthorizedException e) {
			error.setErrorCode(ErrorCode.unauthorizedException);
			error.setErrorMessage("Unauthorized action");
			
			response.setSuccess(false);
			response.setError(error);
			logger.warn("[UnauthorizedException] Unauthorized action : " + e.getMessage());

		} catch(Exception e) {
			error.setErrorCode(ErrorCode.baseException);
			error.setErrorMessage("Unknown error occured, please try again later");
			
			response.setSuccess(false);
			response.setError(error);
			logger.error("[Exception] Unknown error occured : " + e.getMessage());
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
	@RequestMapping(value = "create", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Response<Session> createUser (@RequestBody RequestUserCreate requestUserCreate) {
		
		Response<Session> response = new Response<Session>();
		ErrorDetail error = new ErrorDetail();
		
		try {
			logger.info("calling PizzaHouse /create endpoint : " + requestUserCreate.getUsername());
			if (requestUserCreate.getUsername() == null || requestUserCreate.getFirstName() == null || requestUserCreate.getFirstName() == null || requestUserCreate.getPassword() == null) {
				throw new UserProfileException("Required parameter cannot be empty");
			}
			
			response = userService.createUser(requestUserCreate.getUsername(), requestUserCreate.getFirstName(), requestUserCreate.getLastName(), requestUserCreate.getPassword());
			logger.info("Finish calling PizzaHouse /create endpoint : " + mapper.writeValueAsString(response));
		} catch(RollbackException e) {
			error.setErrorCode(ErrorCode.rollbackException);
			error.setErrorMessage("Database error, transaction has been rejected and rolled back");
			
			response.setSuccess(false);
			response.setError(error);
			logger.warn("[RollbackException] transaction has been rejected and rolled back" + e.getMessage());
		} catch(UserProfileException e) {
			error.setErrorCode(ErrorCode.userProfileException);
			error.setErrorMessage(e.getMessage());
			
			response.setSuccess(false);
			response.setError(error);
			logger.warn("[UserProfileException] User Profile handling error : " + e.getMessage());
		} catch(NoSuchAlgorithmException e) {
			error.setErrorCode(ErrorCode.noSuchAlgorithmException);
			error.setErrorMessage("Database error, transaction has been rejected and rolled back");
			
			response.setSuccess(false);
			response.setError(error);
			logger.warn("[NoSuchAlgorithmException] Database error, transaction has been rejected and rolled back : " + e.getMessage());
		} catch(Exception e) {
			error.setErrorCode(ErrorCode.baseException);
			error.setErrorMessage("Unknown error occured, please try again later");
			
			response.setSuccess(false);
			response.setError(error);
			logger.error("[Exception] Unknown error occured : " + e.getMessage());
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
	@RequestMapping(value = "order", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Response<String> submitOrder (@RequestHeader("uuid") String userUuid, @RequestHeader("session") String session, @RequestBody Order order) {

		Response<String> response = new Response<String>();
		ErrorDetail error = new ErrorDetail();
		
		try {
			logger.info("calling PizzaHouse /order endpoint : " + userUuid);
			if (order == null) {
				throw new OrderFullfillmentException("Required parameter cannot be empty");
			}
			
			securityService.checkUserTokenByUserUuid(userUuid, session, Default.sessionTokenExpirationDays);
			response = orderService.submitOrder(userUuid, order);
			logger.info("Finish calling PizzaHouse /order endpoint : " + mapper.writeValueAsString(response));
		} catch(RollbackException e) {
			error.setErrorCode(ErrorCode.rollbackException);
			error.setErrorMessage("Database error, transaction has been rejected and rolled back");
			
			response.setSuccess(false);
			response.setError(error);
			logger.warn("[RollbackException] transaction has been rejected and rolled back : " + e.getMessage());
		} catch(DatabaseUnavailableException e) {
			error.setErrorCode(ErrorCode.databaseUnavailableException);
			error.setErrorMessage("Database connection error, cannot obtain DB session");
			
			response.setSuccess(false);
			response.setError(error);
			logger.error("[DatabaseUnavailableException] Database connection error, cannot obtain DB session" + e.getMessage());
		} catch(UnauthorizedException e) {
			error.setErrorCode(ErrorCode.unauthorizedException);
			error.setErrorMessage(e.getMessage());
			
			response.setSuccess(false);
			response.setError(error);
			logger.warn("[UnauthorizedException] Unauthorized action : " + e.getMessage());
		} catch(OrderFullfillmentException e) {
			error.setErrorCode(ErrorCode.orderFullfillmentException);
			error.setErrorMessage(e.getMessage());
			
			response.setSuccess(false);
			response.setError(error);
			logger.warn("[OrderFullfillmentException] Processing error during order fullfillment : " + e.getMessage());

		} catch(Exception e) {
			error.setErrorCode(ErrorCode.baseException);
			error.setErrorMessage("Unknown error occured, please try again later");
			
			response.setSuccess(false);
			response.setError(error);
			logger.error("[Exception] Unknown error occured : " + e.getMessage());
		}
		return response;
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler({ Exception.class })
	public Response<String> pathNotFound (RuntimeException ex, WebRequest request) {

		Response<String> response = new Response<String>();
		ErrorDetail error = new ErrorDetail();
		error.setErrorCode(ErrorCode.baseException);
		error.setErrorMessage("Path not found");
		
		response.setSuccess(false);
		response.setError(error);
		
		return response;
	}
	
    public static void main(String[] args) {
    	System.out.println("WebConfiguration run init");


    }
}
