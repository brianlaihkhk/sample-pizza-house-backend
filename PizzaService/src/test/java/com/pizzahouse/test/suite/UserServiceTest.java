package com.pizzahouse.test.suite;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pizzahouse.common.database.DatabaseQuery;
import com.pizzahouse.common.entity.Session;
import com.pizzahouse.common.entity.User;
import com.pizzahouse.common.exception.UnauthorizedException;
import com.pizzahouse.common.exception.UserProfileException;
import com.pizzahouse.service.initialization.WebConfiguration;
import com.pizzahouse.test.data.UserTestData;
import com.pizzahouse.common.model.Response;
import com.pizzahouse.common.security.SecurityService;
import com.pizzahouse.service.controller.UserService;
import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=WebConfiguration.class)
public class UserServiceTest {
	private static DatabaseQuery<User> userQuery = new DatabaseQuery<User>();
	private static DatabaseQuery<Session> sessionQuery = new DatabaseQuery<Session>();
	private UserService userService = new UserService();
	private SecurityService securityService = new SecurityService();

	/**
	 * Validate DB connection has been established before UserService Test
	 */
    @BeforeClass
    public static void instantiate() {
    	assertEquals(true, userQuery.checkConnection());
    }
    
	/**
	 * Remove testing data after test complete
	 */
    @AfterClass
    public static void removeRecord() {
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
	 */
    @Test
    public void _02_testUserLoginByUsername() throws UnauthorizedException, NoSuchAlgorithmException, UserProfileException {
    	Response<Session> jamesResponse = userService.userLogin(UserTestData.james.getUsername(), UserTestData.james.getPassword());
    	assertEquals(true, jamesResponse.isSuccess());
    	assertEquals(UserTestData.jamesSession.getToken(), jamesResponse.getPayload().getToken());
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
	 */
    @Test(expected = UnauthorizedException.class)
    public void _04_testUserLoginExpirationByUsername() throws UnauthorizedException, UserProfileException {
    	securityService.checkUserTokenByUsername(UserTestData.james.getUsername(), UserTestData.jamesSession.getToken(), (long) 0);
    }
  
	/**
	 * Test user login by user Id
	 */
    @Test
    public void _05_testUserLoginByUserId() throws UnauthorizedException {
    	assertEquals(true, securityService.checkUserTokenByUserId(UserTestData.james.getId(), UserTestData.jamesSession.getToken(), (long) 30));
    }
    

	/**
	 * Test user cannot login by incorrect session token
	 */
    @Test(expected = UnauthorizedException.class)
    public void _06_testUsernameLoginByIncorrectSessionToken() throws UnauthorizedException, UserProfileException {
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
