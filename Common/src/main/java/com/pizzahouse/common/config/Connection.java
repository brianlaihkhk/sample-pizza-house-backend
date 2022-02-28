package com.pizzahouse.common.config;

public class Connection {
	public static String hibernateConfigFilename = "hibernate.cfg.xml";

	public static String pizzaServiceHost;
	public static String orderConfirmationServiceHost;

	public static String serverJwtSecretKey;
	public static String serverIssuerName;
	public static long jwtTtlMilliseconds;

	public static String orderConfirmationServiceName;
}
