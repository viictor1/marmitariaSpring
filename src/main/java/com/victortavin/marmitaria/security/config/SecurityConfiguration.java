package com.victortavin.marmitaria.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {
    
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
    private AuthenticationProvider authenticationProvider;
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http
    	.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authorize -> authorize
        	.requestMatchers("/roles").authenticated()
        	.requestMatchers("/auth/**").permitAll()
            .anyRequest().permitAll()
        )
        .sessionManagement(sessionManagement -> sessionManagement
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .authenticationProvider(authenticationProvider)
    	.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
        
    
}
