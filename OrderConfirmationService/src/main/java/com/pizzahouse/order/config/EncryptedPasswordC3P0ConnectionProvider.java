package com.pizzahouse.order.config;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.util.Map;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.spec.PBEParameterSpec;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.c3p0.internal.C3P0ConnectionProvider;
import org.jasypt.commons.CommonUtils;
import org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64;
import org.jasypt.encryption.pbe.PBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.exceptions.EncryptionInitializationException;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.hibernate5.connectionprovider.ParameterNaming;
import org.jasypt.hibernate5.encryptor.HibernatePBEEncryptorRegistry;
import org.jasypt.hibernate5.encryptor.HibernatePBEStringEncryptor;
import org.jasypt.properties.PropertyValueEncryptionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EncryptedPasswordC3P0ConnectionProvider extends C3P0ConnectionProvider {
	 
    private static final long serialVersionUID = 5273353009914873806L;
    private ObjectMapper mapper = new ObjectMapper();

    public EncryptedPasswordC3P0ConnectionProvider() {
        super();
    }
    
    
    public void configure(final Map props) {
        // Get the original values, which may be encrypted
        final String driver = (String) props.get(AvailableSettings.DRIVER);
        final String url = (String) props.get(AvailableSettings.URL);
        final String user = (String) props.get(AvailableSettings.USER);
        final String password = (String) props.get(AvailableSettings.PASS);
        
        if (props.get("jasypt.encryptor.password") != null) {
	        final HibernatePBEStringEncryptor encryptor = new HibernatePBEStringEncryptor();
	        encryptor.setAlgorithm("PBEWITHMD5ANDDES");
	        encryptor.setPassword((String) props.get("jasypt.encryptor.password"));
	
	
	
	       props.put(AvailableSettings.USER, encryptor.decrypt(user));
	       props.put(AvailableSettings.PASS, encryptor.decrypt(password));
	       
        } else {
        	System.out.println("No encryption is applied");
        }

       // Let Hibernate do the rest
       super.configure(props);
    } 
 
}
