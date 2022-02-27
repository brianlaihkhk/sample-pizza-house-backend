package com.pizzahouse.order.initialization;


import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.pizzahouse.common.config.Connection;
import com.pizzahouse.order.controller.Router;

import org.apache.log4j.varia.Roller;
import org.hibernate.Session;

@Configuration
public class WebConfiguration {
	private SessionFactory sessionFactory = null;
	
	@Bean
    public Logger produceLogger(InjectionPoint injectionPoint) {
    	System.out.println("produceLogger");
        Class<?> classOnWired = injectionPoint.getMember().getDeclaringClass();
        return LoggerFactory.getLogger(classOnWired);
    	// return LoggerFactory.getLogger(Roller.class);
    }

    @Bean
    public Session produceDbConnection() {
    	System.out.println("produceDbConnection");
    	if (sessionFactory == null) {
    		sessionFactory = generateSessionFactory();
    	}
        return sessionFactory.openSession();
    }
    
    private SessionFactory generateSessionFactory() {
    	org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.configure(Connection.hibernateConfigFilename);
        
        Reflections reflections = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forJavaClassPath()));
        Set<Class<?>> types = reflections.getTypesAnnotatedWith(javax.persistence.Entity.class);
        types.forEach(clazz -> configuration.addAnnotatedClass(clazz));
           
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(ssrb.build());
    }
    
}
