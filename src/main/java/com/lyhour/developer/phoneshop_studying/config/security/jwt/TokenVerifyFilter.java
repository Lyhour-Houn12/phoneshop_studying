package com.lyhour.developer.phoneshop_studying.config.security.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TokenVerifyFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if(path.equals("/login") || path.equals("/users") && request.getMethod().equals("POST") ) {
			filterChain.doFilter(request, response);
			return;
		}
				
		String authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
		    filterChain.doFilter(request, response);
		    return;
		}
		String token = authorizationHeader.replace("Bearer ", "");
		String secretKey = "sfdhjlg342lsadfsfdhjlg342lsadfsfdhjlg342lsadfsfdhjlg342lsadf";
		Claims body = Jwts.parserBuilder()
			    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
			    .build()
			    .parseClaimsJws(token)
			    .getBody();
		String username = body.getSubject();
		List<String> authority = (List<String>) body.get("authorities");
		
		
		Set<SimpleGrantedAuthority> grantedAuthority = authority.stream()
			.map(SimpleGrantedAuthority::new)
			.collect(Collectors.toSet());
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,grantedAuthority);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
		
		
	}
	

}
