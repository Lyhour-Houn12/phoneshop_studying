package com.lyhour.developer.phoneshop_studying.config.security.jwt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter{
	public JwtLoginFilter(AuthenticationManager authenticationManager) {
        // Use the parent class setter
        super.setAuthenticationManager(authenticationManager);
    }
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			LoginRequest loginRequest = mapper.readValue(request.getInputStream(), LoginRequest.class);
			Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
			return this.getAuthenticationManager().authenticate(authentication);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
				
	}
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String secretKey = "sfdhjlg342lsadfsfdhjlg342lsadfsfdhjlg342lsadfsfdhjlg342lsadf";
		List<String> authorities = authResult.getAuthorities()
			.stream()
			.map(GrantedAuthority::getAuthority)
			.toList();
		
		String token = Jwts.builder()
			.setSubject(authResult.getName())
			.claim("authorities", authorities)
			.setIssuedAt(new Date())
			.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(7)))
			.setIssuer("phoneshop.com")
			.signWith(
					Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8))
				)
			.compact();
		
		
		response.setHeader("Authorization", "Bearer " +token);
	}
}
