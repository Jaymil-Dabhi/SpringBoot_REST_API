package com.example.springrestapi.config;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;

import com.example.config.Authentication;
import com.example.config.BadCredentialsException;
import com.example.config.Claims;
import com.example.config.FilterChain;
import com.example.config.GrantedAuthority;


import com.example.config.JwtConstant;

import com.example.config.UsernamePasswordAuthenticationToken;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidator extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwt = request.getHeader(JwtConstant.JWT_HEADER);
		
		if(jwt!=null) {
			jwt=jwt.substring(7);
			try {
				SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
				
				Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
				
				String email = String.valueOf(claims.get("email"));
				
				String authorities = String.valueOf(claims.get("authorities"));
				
				List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
				Authentication authentication = new UsernamePasswordAuthenticationToken(email,null,auths);
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			}catch(Exception e) {
				throw new BadCredentialsException("invalid token... from jwt validator");
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
