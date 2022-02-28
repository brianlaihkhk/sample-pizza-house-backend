package com.pizzahouse.common.config;

public class ErrorCode {
	public static int rollbackException = 406;
	public static int orderFullfillmentException = 440;
	public static int baseException = 501;
	public static int unauthorizedException = 401;
	public static int userProfileException = 450;
	public static int noSuchAlgorithmException = 408;
	public static int jsonProcessingException = 422;
	public static int databaseUnavailableException = 502;
	public static int jsonMappingException = 422;
	public static int jwtMessageExpiredException = 384;
	public static int jwtIssuerNotMatchException = 385;
}
