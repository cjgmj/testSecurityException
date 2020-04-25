package com.cjgmj.testSecurityException.utils;

import java.security.Key;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtConfig {

	public static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	public static final String HEADER = "Authorization";
	public static final String PREFIX = "Bearer ";

}
