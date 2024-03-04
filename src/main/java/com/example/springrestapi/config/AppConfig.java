package com.example.springrestapi.config;

import java.util.Arrays;
import java.util.Collections;


import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.config.BasicAuthenticationFilter;


import com.example.config.JwtValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.config.SecurityFilterChain;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@ComponentScan(basePackages = "com.example.springrestapi.service")
public class AppConfig<HttpSecurity, SecurityFilterChain, PasswordEncoder> {

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    	http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() 
    	.authorizeHttpRequests(Authorize->Authorize.requestMatchers("/api/**").authenticated().anyRequest().permitAll())
    	.addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
    	.csrf().disable()
    	.cors().configurationSource(new CorsConfigurationSource() {
    		
    		@Override
    		public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
    			
    			CorsConfiguration cfg = new CorsConfiguration();
    			
    			cfg.setAllowedOrigins(Arrays.asList(
    					"http://localhost:3000",
    					"http://localhost:4200"
    					));
    			cfg.setAllowedMethods(Collections.singletonList("*"));
    			cfg.setAllowCredentials(true);
    			cfg.setAllowedHeaders(Collections.singletonList("*"));
    			cfg.setExposedHeaders(Arrays.asList("Authorization"));
    			cfg.setMaxAge(3600L);
    			return cfg;
    		}
    	})
        .and().httpBasic().and().formLogin();
    	
    	return http.build();
    }
	
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
