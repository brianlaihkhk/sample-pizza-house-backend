package com.pizzahouse.test.suite;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pizzahouse.common.exception.DatabaseUnavailableException;
import com.pizzahouse.common.exception.UnauthorizedException;
import com.pizzahouse.common.exception.UserProfileException;
import com.pizzahouse.service.initialization.WebConfiguration;
import com.pizzahouse.service.security.SecurityService;
import com.pizzahouse.test.data.UserTestData;
import com.pizzahouse.common.model.Response;
import com.pizzahouse.service.controller.UserService;
import com.pizzahouse.service.database.DatabaseQuery;
import com.pizzahouse.service.entity.Session;
import com.pizzahouse.service.entity.User;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/applicationContext.xml")
public class UserServiceTest {
	@Autowired
	protected DatabaseQuery<User> userQuery;
	@Autowired	
	protected DatabaseQuery<Session> sessionQuery;
	@Autowired
	protected UserService userService;
	@Autowired
	protected SecurityService securityService;
	@Autowired
	protected Logger logger;
	
	/**
	 * Validate DB connection has been established before UserService Test
	 */
    @Test
    public void _00_initialize() {
    	assertEquals(true, userQuery.checkConnection());
    }
    
	/**
	 * Remove testing data after test complete
	 */
    @Test
    public void _10_afterTest() {
    	if (UserTestData.jamesSession != null) {
    		userQuery.delete(UserTestData.james);
    		sessionQuery.delete(UserTestData.jamesSession);
    	}
    	if (UserTestData.janetSession != null) {
    		userQuery.delete(UserTestData.janet);
    		sessionQuery.delete(UserTestData.janetSession);
    	}
    	if (UserTestData.annaSession != null) {
    		userQuery.delete(UserTestData.anna);
    		sessionQuery.delete(UserTestData.annaSession);
    	}
    	if (UserTestData.peterSession != null) {
    		userQuery.delete(UserTestData.peter);
    		sessionQuery.delete(UserTestData.peterSession);

    	}
    	assertEquals(true, true);

    }

	/**
	 * Create user in the DB
	 * @throws UserProfileException 
	 * @throws NoSuchAlgorithmException 
	 */
    @Test
    public void _01_createUserTest() throws NoSuchAlgorithmException, UserProfileException {
    	Response<Session> jamesResponse = userService.createUser(UserTestData.james.getUsername(), UserTestData.james.getFirstName(), UserTestData.james.getLastName(), UserTestData.james.getPassword());
    	assertEquals(true, jamesResponse.isSuccess());
    	UserTestData.jamesSession = jamesResponse.getPayload();
    	
    	Response<Session> janetResponse = userService.createUser(UserTestData.janet.getUsername(), UserTestData.janet.getFirstName(), UserTestData.janet.getLastName(), UserTestData.janet.getPassword());
    	assertEquals(true, janetResponse.isSuccess());
    	UserTestData.janetSession = janetResponse.getPayload();

    	Response<Session> annaResponse = userService.createUser(UserTestData.anna.getUsername(), UserTestData.anna.getFirstName(), UserTestData.anna.getLastName(), UserTestData.anna.getPassword());
    	assertEquals(true, annaResponse.isSuccess());
    	UserTestData.annaSession = annaResponse.getPayload();

    	Response<Session> peterResponse = userService.createUser(UserTestData.peter.getUsername(), UserTestData.peter.getFirstName(), UserTestData.peter.getLastName(), UserTestData.peter.getPassword());
    	assertEquals(true, peterResponse.isSuccess());
    	UserTestData.peterSession = peterResponse.getPayload();

    }
    
	/**
	 * Test user can login
	 * @throws UnauthorizedException 
	 * @throws UserProfileException 
	 * @throws NoSuchAlgorithmException 
	 * @throws DatabaseUnavailableException 
	 */
    @Test
    public void _02_testUserLoginByUsername() throws UnauthorizedException, NoSuchAlgorithmException, UserProfileException, DatabaseUnavailableException {
    	Response<Session> jamesResponse = userService.userLogin(UserTestData.james.getUsername(), UserTestData.james.getPassword());
    	assertEquals(true, jamesResponse.isSuccess());
    }

	/**
	 * Test user cannot login after expiration
	 */
    @Test(expected = UnauthorizedException.class)
    public void _03_testUserLoginExpirationByUserId() throws UnauthorizedException {
    	securityService.checkUserTokenByUserId(UserTestData.james.getId(), UserTestData.jamesSession.getToken(), (long) 0);
    }

	/**
	 * Test user cannot login after expiration
	 * @throws DatabaseUnavailableException 
	 */
    @Test(expected = UnauthorizedException.class)
    public void _04_testUserLoginExpirationByUsername() throws UnauthorizedException, UserProfileException, DatabaseUnavailableException {
    	securityService.checkUserTokenByUsername(UserTestData.james.getUsername(), UserTestData.jamesSession.getToken(), (long) 0);
    }
  
	/**
	 * Test user login by user Id
	 */
    @Test(expected = UnauthorizedException.class)
    public void _05_testUserLoginByUserId() throws UnauthorizedException {
   	// James Token is changed on _02_
    	assertEquals(true, securityService.checkUserTokenByUserId(UserTestData.james.getId(), UserTestData.jamesSession.getToken(), (long) 30));
    }
    

	/**
	 * Test user cannot login by incorrect session token
	 * @throws DatabaseUnavailableException 
	 */
    @Test(expected = UnauthorizedException.class)
    public void _06_testUsernameLoginByIncorrectSessionToken() throws UnauthorizedException, UserProfileException, DatabaseUnavailableException {
    	securityService.checkUserTokenByUsername(UserTestData.james.getUsername(), "123abc", (long) 30);
    }    

	/**
	 * Test user cannot login by incorrect session token
	 */
    @Test(expected = UnauthorizedException.class)
    public void _07_testUserIdLoginByIncorrectSessionToken() throws UnauthorizedException {
    	securityService.checkUserTokenByUserId(UserTestData.james.getId(), "123abc", (long) 30);
    } 
}
