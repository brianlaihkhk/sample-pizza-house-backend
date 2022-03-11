package com.pizzahouse.service.controller;

import com.pizzahouse.service.database.DatabaseQuery;
import com.pizzahouse.service.entity.Session;
import com.pizzahouse.service.entity.User;
import com.pizzahouse.service.security.SecurityService;
import com.pizzahouse.common.model.Response;
import com.pizzahouse.common.exception.DatabaseUnavailableException;
import com.pizzahouse.common.exception.UnauthorizedException;
import com.pizzahouse.common.exception.UserProfileException;

import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UserService {
	@Autowired
	protected org.slf4j.Logger logger;
	@Autowired
	protected SecurityService securityService;
	@Autowired
	protected DatabaseQuery<User> userQuery;
	
	/**
	 * Login function
	 * @param username Username input from user during login
	 * @param password Password input from user during login (SHA256 salt)
	 * @return Success with session token / fail with error message
	 * @throws UserProfileException 
	 * @throws NoSuchAlgorithmException 
	 * @throws DatabaseUnavailableException 
	 */
	public Response<Session> userLogin (String username, String password) throws UnauthorizedException, UserProfileException, NoSuchAlgorithmException, DatabaseUnavailableException {
		Response<Session> response = new Response<Session>();
		logger.debug(username);
		logger.debug(password);

		User user = securityService.getUserByUsername(username);
		logger.debug(user.getPassword());

		if (user != null && user.getPassword() != null) {
			if (!user.getPassword().equals(password)) {
				throw new UnauthorizedException("Invalid username/password for user");
			}
			response.setSuccess(true);
			response.setPayload(securityService.refreshSession(user));
		} else {
			throw new UnauthorizedException("Invalid username/password for user");
		}
		
		return response;

	}

	/**
	 * Create new user
	 * @param username Username input from user during registration
	 * @param firstname First name input from user during registration
	 * @param lastname Last name input from user during registration
	 * @param password Password input from user during registration  (SHA256 salt)
	 * @return Success with session token / fail with error message
	 */
	public Response<Session> createUser (String username, String firstName, String lastName, String password) throws NoSuchAlgorithmException, UserProfileException {
		Response<Session> response = new Response<Session>();

		User user = new User (username, firstName, lastName, password);
		user.setUuid(UUID.randomUUID().toString());
		String id = (String) userQuery.insert(user);
	
		if (id != null && !id.isEmpty()) {
			response.setSuccess(true);
			response.setPayload(securityService.createSession(user.getUuid()));
		} else {
			throw new UserProfileException("Unable to create new user. User may be registered or username may be taken by other users");
		}
		
		return response;
	}

}
