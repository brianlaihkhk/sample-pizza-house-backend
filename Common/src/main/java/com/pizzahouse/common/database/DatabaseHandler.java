package com.pizzahouse.common.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.pizzahouse.common.config.Connection;
import com.pizzahouse.common.config.Default;

public class DatabaseHandler {

	protected Session dbSession;
	
	DatabaseHandler() {
		Configuration configuration = new Configuration();
        configuration.configure(Connection.hibernateConfigFilename);
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
        dbSession = sessionFactory.openSession();
	}
	
	public Session getDbSession() {
		return dbSession;
	}
	
	public boolean checkConnection() {
		return dbSession.isConnected();
	}

}
