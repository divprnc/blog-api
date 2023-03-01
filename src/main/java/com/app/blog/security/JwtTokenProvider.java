package com.app.blog.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.app.blog.exception.BlogApiException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
	@Value("${app.jwt-secret}")
	private String jwtSecret;
	@Value("${app.jwt-expiration-milliseconds}")
	private Long jwtExpirationDate;

	// generate JWT token
	public String generateToken(Authentication authentication) {
		String username = authentication.getName();
		Date currentDate = new Date();
		Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

		String jwtToken = Jwts.builder().setSubject(username).setIssuedAt(expireDate).setExpiration(expireDate).signWith(key()).compact();
		return jwtToken;
	}

	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}
	
	// get Username from JWT Token
	public String getUsername(String token) {
		Claims claims =  Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();
		
		String username = claims.getSubject();
		return username;
	}
	
	
	// Balidate JWT Token
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
			return true;
		} catch(MalformedJwtException exception) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Invalid JWT token.");
		} catch(ExpiredJwtException exception) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Expired JWT token.");
		} catch(UnsupportedJwtException exception) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Unsupported JWT token.");
		} catch(IllegalArgumentException exception) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
		}
	}
	

}
