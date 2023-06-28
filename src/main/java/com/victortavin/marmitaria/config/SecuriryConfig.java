package com.victortavin.marmitaria.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.victortavin.marmitaria.filters.TokenFilter;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecuriryConfig {
	
	@Autowired
	private TokenFilter tokenFilter;
	
    public static final String[] PUBLIC_PATHS = {
    		"/api/auth/**",
            "/v3/api-docs.yaml",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/users/login",
            "/users/cadastro",
            "/h2-console/**",
            "swagger-ui/**"
            };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    	return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req ->{
                	req.requestMatchers(PUBLIC_PATHS).permitAll();
                })
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    	return authenticationConfiguration.getAuthenticationManager();
    }
}
