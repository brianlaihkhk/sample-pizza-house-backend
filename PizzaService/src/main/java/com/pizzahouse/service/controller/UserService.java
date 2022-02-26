package com.pizzahouse.service.controller;

import com.pizzahouse.common.security.SecurityService;
import com.pizzahouse.common.model.Response;
import com.pizzahouse.common.database.DatabaseQuery;
import com.pizzahouse.common.entity.Session;
import com.pizzahouse.common.entity.User;
import com.pizzahouse.common.exception.UnauthorizedException;
import com.pizzahouse.common.exception.UserProfileException;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.criteria.Predicate;

public class UserService {
	private DatabaseQuery<User> userQuery = new DatabaseQuery<User>();
	private SecurityService securityService = new SecurityService();

	/**
	 * Login function
	 * @param username Username input from user during login
	 * @param password Password input from user during login (SHA256 salt)
	 * @return Success with session token / fail with error message
	 */
	public Response<Session> userLogin (String username, String password) throws UnauthorizedException {
		Response<Session> response = new Response<Session>();

		Predicate[] predicates = new Predicate[1];
		predicates[0] = userQuery.getCriteriaBuilder().equal(userQuery.getRoot(User.class).get("username"), username);
		List<User> result = userQuery.selectByCriteria(User.class, predicates);
		
		if (result.size() == 1 && result.get(0).getPassword() == password) {
			response.setSuccess(true);
			response.setPayload(result.get(0).getSession());
		} else {
			throw new UnauthorizedException("Cannot obtain user by username");
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
		user = securityService.setToken(user);
		int id = userQuery.insert(user);

		if (id > 0) {
			response.setSuccess(true);
			response.setPayload(userQuery.select(User.class, id).getSession());
		} else {
			throw new UserProfileException("Unable to create new user. User may be registered or username may be taken by other users");
		}
		
		return response;
	}

}
