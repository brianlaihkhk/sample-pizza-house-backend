package com.pizzahouse.service.initialization;


import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzahouse.common.config.Default;
import com.pizzahouse.service.config.Connection;

@Configuration
public class WebConfiguration{
	private SessionFactory sessionFactory = null;
	private ObjectMapper mapper = new ObjectMapper();

	@Bean
    public org.slf4j.Logger produceLogger(InjectionPoint injectionPoint) {
    	System.out.println("produceLogger start");
        Class<?> classOnWired = injectionPoint.getMember().getDeclaringClass();
    	System.out.println("produceLogger Finish");
        return LoggerFactory.getLogger(classOnWired);
    	// return LoggerFactory.getLogger(Roller.class);
    }

    @Bean
    public Session produceDbConnection() {
    	System.out.println("produceDbConnection start");
    	if (sessionFactory == null) {
    		sessionFactory = generateSessionFactory();
    	}
    	
    	System.out.println("produceDbConnection finish");
        return sessionFactory.openSession();
    }
    
    private SessionFactory generateSessionFactory() {
    	org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.configure(Default.hibernateConfigFilename);

        Reflections reflections = new Reflections(Default.pizzaServiceEntityPackagePath);
        Set<Class<? extends Object>> types = reflections.getTypesAnnotatedWith(javax.persistence.Entity.class);
        types.forEach(clazz -> configuration.addAnnotatedClass(clazz));
        
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(ssrb.build());
    }
}
