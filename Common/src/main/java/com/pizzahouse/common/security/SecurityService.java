package com.pizzahouse.common.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Predicate;

import org.apache.commons.codec.binary.Hex;

import com.pizzahouse.common.database.DatabaseQuery;
import com.pizzahouse.common.entity.Session;
import com.pizzahouse.common.entity.User;
import com.pizzahouse.common.exception.UnauthorizedException;

public class SecurityService {
	DatabaseQuery<User> userQuery = new DatabaseQuery<User>();

	public boolean checkUserTokenByUsername (String username, String token) throws UnauthorizedException {
		long epochValidTime = (System.currentTimeMillis() / 1000) - 30 * 24 * 3600;

		Map<String, Object> queryList = new HashMap<String, Object>();
		queryList.put("username", username);
		
//		List<User> result = dbTransaction.selectByParameter("findUserByUsername", queryList);

		Predicate[] predicates = new Predicate[1];
		predicates[0] = userQuery.getCriteriaBuilder().equal(userQuery.getRoot(User.class).get("username"), username);
		List<User> result = userQuery.selectByCriteria(User.class, predicates);
		
		if (result.size() == 1 && result.get(0).getSession() != null && result.get(0).getSession().getToken() == token && result.get(0).getSession().getCreationEpochTime() > epochValidTime) {
			return true;
		} else if (result.get(0).getSession().getCreationEpochTime() < epochValidTime) {
			throw new UnauthorizedException("Token expired, unauthorized action");
		}
		throw new UnauthorizedException("Token not match, unauthorized action");
	}
	

	public boolean checkUserTokenByUserId (int userId, String token) throws UnauthorizedException {
		long epochValidTime = (System.currentTimeMillis() / 1000) - 30 * 24 * 3600;	
		
		User user = new User();
		user = userQuery.select(User.class, userId);
				
		if (user.getSession() != null && user.getSession().getToken() == token && user.getSession().getCreationEpochTime() > epochValidTime) {
			return true;
		}  else if (user.getSession().getCreationEpochTime() < epochValidTime) {
			throw new UnauthorizedException("Token expired, unauthorized action");
		}
		return false;
	}
	
	public User setToken (User user) throws NoSuchAlgorithmException {
		Long epoch = (System.currentTimeMillis() / 1000);
		Session session = new Session();
		
		session.setCreationEpochTime(epoch);
		session.setCreationTime(new Date(epoch * 1000));
		session.setToken(generateToken(epoch.toString()));
		user.setSession(session);
		return user;
	}	
	
	private String generateToken (String input) throws NoSuchAlgorithmException {
		MessageDigest digest;

		digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
		String sha256hex = new String(Hex.encodeHex(hash));
		
		return sha256hex;
	}
}
