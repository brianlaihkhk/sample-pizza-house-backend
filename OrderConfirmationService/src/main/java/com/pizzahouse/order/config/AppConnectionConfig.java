package com.pizzahouse.order.config;

import org.springframework.context.annotation.Configuration;

import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;

@Configuration
@EncryptablePropertySource("connection.properties")
public class AppConnectionConfig {

}
