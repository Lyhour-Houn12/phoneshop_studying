package com.lyhour.developer.phoneshop_studying.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.lyhour.developer.phoneshop_studying.config.security.jwt.JwtLoginFilter;
import com.lyhour.developer.phoneshop_studying.config.security.jwt.TokenVerifyFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserDetailsService detailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration) throws Exception{
		AuthenticationManager authManager = authenticationConfiguration.getAuthenticationManager();
	    
	    // 2. Create your filter with the valid authManager
	    JwtLoginFilter jwtLoginFilter = new JwtLoginFilter(authManager);
	    
	    // (Ideally, set the filter processing URL here if you want it to be specific, e.g., /api/login)
	    jwtLoginFilter.setFilterProcessesUrl("/login");
		
		http.csrf(csrf -> csrf.disable());
		http.addFilter(jwtLoginFilter);
		http.addFilterAfter(new TokenVerifyFilter(), JwtLoginFilter.class);
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers(HttpMethod.POST, "/users").permitAll()
				.requestMatchers(HttpMethod.POST, "/brands").hasAuthority(PermissionEnum.BRANE_WRITE.getDescription())
				.requestMatchers(HttpMethod.GET, "/brands").hasAuthority(PermissionEnum.BRAND_READ.getDescription())
				.requestMatchers(HttpMethod.POST, "/models").hasAuthority(PermissionEnum.MODEL_WRITE.getDescription())
				.requestMatchers(HttpMethod.GET, "/models").hasAuthority(PermissionEnum.MODEL_READ.getDescription())
				.anyRequest().authenticated());
		
		
		return http.build();
		
	}
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(getAuthenticationProvider());
	}
	@Bean
	public AuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(detailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return authenticationProvider;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * @Bean public UserDetailsService detailsService () { UserDetails user1 =
	 * User.builder() .username("lyhour")
	 * .password(passwordEncoder.encode("lyhour123"))
	 * .authorities(RoleEnum.ADMIN.authorities()) .build(); UserDetails user2 =
	 * User.builder() .username("jing") .password(passwordEncoder.encode("jing123"))
	 * .authorities(RoleEnum.SALE.authorities()) .build(); UserDetailsService
	 * detailsService = new InMemoryUserDetailsManager(user1, user2); return
	 * detailsService;
	 * 
	 * }
	 */
	
	
}
