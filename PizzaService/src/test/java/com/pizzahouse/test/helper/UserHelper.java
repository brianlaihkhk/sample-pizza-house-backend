package com.pizzahouse.test.helper;

import java.sql.Date;

import com.pizzahouse.service.entity.Session;
import com.pizzahouse.service.entity.User;

public class UserHelper {
	
	/**
	 * Create User Object
	 * @param userId User ID
	 * @param username Username of the user
	 * @param firstName First name of the user
	 * @param lastName Last name of the user
	 * @param session Session token of the user
	 * @return OrderDetail object
	 */
 	public static User createUser(String username, String firstName, String lastName, String password){
 		User user = new User(username, firstName, lastName, password);
		
 		return user;
    }

	/**
	 * Create Session information
	 * @param userId User ID
	 * @param token Session token
	 * @param creationEpochTime Create time in epoch format (Using Date column in mysql will have performance issue under high frequency select)
	 * @return Session object
	 */
 	public static Session createSession(int userId, String token, long creationEpochTime){
 		Session session = new Session();
 		session.setUserId(userId);
 		session.setToken(token);
 		session.setCreationEpochTime(creationEpochTime);

 		session.setCreationTime(new Date(creationEpochTime * 1000));
 		return session;
    }
}
