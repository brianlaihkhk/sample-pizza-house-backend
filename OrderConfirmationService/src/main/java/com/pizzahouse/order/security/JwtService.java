package com.pizzahouse.order.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzahouse.common.config.Connection;
import com.pizzahouse.common.exception.DatabaseUnavailableException;
import com.pizzahouse.common.exception.JwtIssuerNotMatchException;
import com.pizzahouse.common.exception.JwtMessageExpiredException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService<T> {
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	protected Logger logger;
	
	/**
	 * Generate encrypted Jwt message
	 * @param id message Id
	 * @param issuer Issuer
	 * @param subject Subject of the encoded message
	 * @param payload Payload of the encoded message
	 * @param ttlMillis Expiration ttl of the message
	 * @param secretKey Secret key
	 * @return Encoded message string
	 * 
	 */
	public String createJwt(String id, String issuer, String payload, long ttlMillis, String secretKey) {
		  
	    //The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);

	    //We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

	    //Let's set the JWT Claims
	    JwtBuilder builder = Jwts.builder().setId(id)
	            .setIssuedAt(now)
	            .setSubject(payload)
	            .setIssuer(issuer)
	            .signWith(signatureAlgorithm, signingKey);
	  
	    if (ttlMillis > 0) {
	        long expMillis = nowMillis + ttlMillis;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }  
	  
	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}
	
	/**
	 * Decode encrypted Jwt message
	 * @param jwt Encoded Jwt message
	 * @param secretKey Secret key
	 * @return Encoded message string
	 * 
	 */
	public Jws<Claims> decodeJWT(String jwt, String secretKey) {
	    //This line will throw an exception if it is not a signed JWS (as expected)
	    Jws<Claims> claims = Jwts.parser()
	            .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
	            .parseClaimsJws(jwt);
	    return claims;
	}
	
	
	/**
	 * Decode encrypted Jwt message
	 * @param clazz Class of the T
	 * @param jwt Jwt message
	 * @return Object T
	 * 
	 */
	public T decodeMessage(Class<T> clazz, String jwt) throws JwtMessageExpiredException, JwtIssuerNotMatchException, JsonMappingException, JsonProcessingException {
	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);
		
		Jws<Claims> jwsClaims = decodeJWT(jwt, Connection.serverJwtSecretKey);
		Claims claims = jwsClaims.getBody();
		
		if (claims.getExpiration().before(now)) {
			throw new JwtMessageExpiredException("Encoded message has been expired");
		}

		if (!claims.getIssuer().trim().equals(Connection.serverIssuerName.trim())) {
			throw new JwtIssuerNotMatchException("Issuer of the encoded message does not match");
		}
		
		return (T) mapper.readValue(claims.getSubject(), clazz);

	}
}
