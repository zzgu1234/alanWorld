package com.bokduck.component;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private String secretKey = "bokduck@JSONWebToken@alan.kang";


	public String makeJwtString(Map<String, Object> tokenData,
			String expireTimeType) throws Exception {
		return makeJwtString(tokenData, getExpireTime(expireTimeType));
	}

	private String makeJwtString(Map<String, Object> tokenData,
			Date expireTime) {

		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		Map<String, Object> headerMap = new HashMap<String, Object>();
        headerMap.put("typ","JWT");
        headerMap.put("alg","HS256");

        JwtBuilder builder = Jwts.builder()
        		.setHeader(headerMap)
        		.setClaims(tokenData)
        		.setExpiration(expireTime)
        		.signWith(signatureAlgorithm, signingKey);


		return builder.compact();
	}

	private Date getExpireTime(String expireTimeType) {

    	Calendar cal = Calendar.getInstance();
    	if( "ACCESS".equals(expireTimeType) ) { // ACCESS
    	}

		return cal.getTime();
	}

	public int validateJwt(String jwtString) throws Exception {

		try {

			Claims claims = Jwts.parser()
					.setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
					.parseClaimsJws(jwtString)
					.getBody();

			return 0;
		} catch (ExpiredJwtException e) {
			// 토큰만료
			return 1;
		} catch (JwtException e) {
			// 토큰변조
			return 2;
		} catch (Exception e) {
			return 3;
		}

	}

	public Claims getTokenData(String jwtString) throws Exception {
		try {
			return Jwts.parser()
					.setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
					.parseClaimsJws(jwtString)
					.getBody();
		} catch (Exception e) {
			return null;
		}
	}

}
