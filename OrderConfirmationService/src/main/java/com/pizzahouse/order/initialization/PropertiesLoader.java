package com.pizzahouse.order.initialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import com.pizzahouse.common.config.Connection;
import com.pizzahouse.common.config.Default;

@Service
public class PropertiesLoader implements ApplicationRunner {
	@Autowired
	protected Logger logger;
	private InputStream connectionInputStream;
	private InputStream defaultInputStream;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		connectionInputStream = PropertiesLoader.class.getClassLoader().getResourceAsStream("connection.properties");
		defaultInputStream = PropertiesLoader.class.getClassLoader().getResourceAsStream("default.properties");

    	populate();

	}

	public InputStream getConnectionInputStream() {
		return connectionInputStream;
	}


	public void setConnectionInputStream(InputStream connectionInputStream) {
		this.connectionInputStream = connectionInputStream;
	}


	public InputStream getDefaultInputStream() {
		return defaultInputStream;
	}


	public void setDefaultInputStream(InputStream defaultInputStream) {
		this.defaultInputStream = defaultInputStream;
	}

	
	public void populate() throws Exception {
		
    	logger.info("Properties Loader start");

    	Properties connectionProperties = getProperties(connectionInputStream);
    	Properties defaultProperties = getProperties(defaultInputStream);

    	
		Connection.pizzaServiceHost = connectionProperties.getProperty("pizzaServiceHost");
		Connection.orderConfirmationServiceHost = connectionProperties.getProperty("orderConfirmationServiceHost");
		Connection.serverJwtSecretKey = connectionProperties.getProperty("serverJwtSecretKey");
		Connection.serverIssuerName = connectionProperties.getProperty("serverIssuerName");
		Connection.jwtTtlMilliseconds = Long.valueOf(connectionProperties.getProperty("jwtTtlMilliseconds"));

		Connection.orderConfirmationServiceName = connectionProperties.getProperty("orderConfirmationServiceName");
		
		
		Default.sessionTokenExpirationDays = Integer.valueOf(defaultProperties.getProperty("sessionTokenExpirationDays"));
		
		Default.subItemCategoryIdPizzaSize = Integer.valueOf(defaultProperties.getProperty("subItemCategoryIdPizzaSize"));
		Default.subItemCategoryIdPizzaTopping = Integer.valueOf(defaultProperties.getProperty("subItemCategoryIdPizzaTopping"));
    	logger.info("Properties Loader end");

	}
	
	public Properties getProperties(InputStream inputStream) {
        Properties prop = new Properties();

		try (InputStream input = inputStream) {
            // load a properties file
            prop.load(input);
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
        return prop;

	}
}
