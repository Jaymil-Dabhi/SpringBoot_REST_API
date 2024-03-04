package com.example.springrestapi.config;

import java.util.Date;

import javax.crypto.SecretKey;

import com.example.config.Authentication;
import com.example.config.Claims;
import com.example.config.JwtConstant;
import com.example.config.Service;

@Service
public class JwtProvider {
	SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
	
   public String generateToken(Authentication auth) {
	   String jwt = Jwts.builder()
			   .setIssuedAt(new Date())
			   .setExpiration(new Date(new Date().getTime()+846000000))
			   .claim("email",auth.getName())
			   .signWith(key).compact();
	   
	   return jwt;
   }
   
   
   public String getEmailFromToken(String jwt) {
	   jwt = jwt.substring(7);
	   
	   Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
	   
	   String email = String.valueOf(claims.get("email"));
	   
	   return email;
   }
}

