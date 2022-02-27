package com.pizzahouse.service.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzahouse.service.database.DatabaseQuery;
import com.pizzahouse.common.entity.Session;
import com.pizzahouse.common.entity.User;
import com.pizzahouse.common.exception.DatabaseUnavailableException;
import com.pizzahouse.common.exception.UnauthorizedException;
import com.pizzahouse.common.exception.UserProfileException;

@Service
public class SecurityService {
	@Autowired
	protected DatabaseQuery<User> userQuery;
	@Autowired
	protected DatabaseQuery<Session> sessionQuery;

	/**
	 * Validate user token by username 
	 * @param username Username of the user
	 * @param token Token input to validate
	 * @return True if user token is verified and not expired, otherwise throw Exception
	 * @throws UserProfileException 
	 * @throws DatabaseUnavailableException 
	 */
	public boolean checkUserTokenByUsername (String username, String sessionToken, long expirationDay) throws UnauthorizedException, UserProfileException, DatabaseUnavailableException {
		User user = getUserByUsername(username);
		return checkUserTokenByUserId(user.getId(), sessionToken, expirationDay);
	}
	
	/**
	 * Validate user token by userId 
	 * @param userId User id of the user
	 * @param token Token input to validate
	 * @return True if user token is verified and not expired, otherwise throw Exception
	 */
	public boolean checkUserTokenByUserId (int userId, String sessionToken, long expirationDay) throws UnauthorizedException {
		long epochValidTime = (System.currentTimeMillis() / 1000) - expirationDay * 24 * 3600;	
		
		Session session = getSession(userId);
				
		if (session != null && session.getToken() == sessionToken) {
			if (session.getCreationEpochTime() < epochValidTime) {
				throw new UnauthorizedException("Token expired, unauthorized action");
			}
			return true;
		}
		throw new UnauthorizedException("Token not match, unauthorized action");
	}
	
	/**
	 * Get user object by username 
	 * @param username Username of the user
	 * @return User object if found, otherwise throw error that cannot find the corresponding user
	 * @throws DatabaseUnavailableException 
	 */
	public User getUserByUsername (String username) throws UserProfileException, DatabaseUnavailableException {
		Predicate[] predicates = new Predicate[1];

		CriteriaBuilder criteriaBuilder = userQuery.getCriteriaBuilder();
		CriteriaQuery<User> cq = criteriaBuilder.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		predicates[0] = criteriaBuilder.equal(root.get("username"), username);
		CriteriaQuery<User> query = cq.select(root).where(predicates);
		
		List<User> result = userQuery.query(query);
		
		if (result.size() == 1) {
			return result.get(0);
		} else {
			throw new UserProfileException("Cannot find the user specified");
		}
	}
	
	/**
	 * Get Session object of the user
	 * @param user User object of the user
	 * @return Session object if found, otherwise null
	 */
	public Session getSession (User user) {
		return getSession(user.getId());
	}

	/**
	 * Get Session object of the user
	 * @param userId Userid of the user
	 * @return Session object if found, otherwise null
	 */
	public Session getSession (int userId) {
		return sessionQuery.selectById(Session.class, userId);
	}

	/**
	 * Set Session object of the user
	 * @param user User object of the user
	 * @return Session object if transaction complete, otherwise throw exception
	 */
	public Session refreshSession (User user) throws NoSuchAlgorithmException {
		return refreshSession(user.getId());
	}
	
	/**
	 * Set Session object of the user by user id
	 * @param user Userid of the user
	 * @return Session object if transaction complete, otherwise throw exception
	 */
	public Session refreshSession (int userId) throws NoSuchAlgorithmException {
		Session session = generateSession(userId);
		sessionQuery.insertOrUpdate(session);
		return session;
	}
	
	/**
	 * Generate session token information to the user object 
	 * @param user User profile object
	 * @return User profile object containing session token information
	 */
	public static Session generateSession (int userId) throws NoSuchAlgorithmException {
		Long epoch = (System.currentTimeMillis() / 1000);
		Session session = new Session();
		
		session.setUserId(userId);
		session.setCreationEpochTime(epoch);
		session.setCreationTime(new Date(epoch * 1000));
		session.setToken(generateToken(epoch.toString()));
		return session;
	}	
	
	
	/**
	 * Session token generation 
	 * @param input Input for generating the session token
	 * @return Generated string using SHA256
	 */
	private static String generateToken (String input) throws NoSuchAlgorithmException {
		MessageDigest digest;

		digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
		String sha256hex = new String(Hex.encodeHex(hash));
		
		return sha256hex;
	}
}
