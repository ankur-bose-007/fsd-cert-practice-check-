package com.practice.security;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.practice.security.SecurityConstants.EXPIRATION_TIME;
import static com.practice.security.SecurityConstants.HEADER_STRING;
import static com.practice.security.SecurityConstants.SECRET;
import static com.practice.security.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	private AuthenticationManager authenticationManager;
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response) throws AuthenticationException{
		Authentication au;
		try{
		com.practice.entity.User creds=new ObjectMapper().readValue(request.getInputStream(), com.practice.entity.User.class);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				creds.getEmail(),creds.getPassword(),new ArrayList()); 
		setDetails(request,token);
		au = authenticationManager.authenticate(token);
		}catch(IOException e){
			logger.error(e);
			throw new RuntimeException(e);
		}
		return au;
	}
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String token=JWT.create().withSubject(((org.springframework.security.core.userdetails.User)authResult.getPrincipal()).getUsername()).withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
		response.setStatus(HttpServletResponse.SC_CREATED);
		chain.doFilter(request, response);
	}
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		super.unsuccessfulAuthentication(request, response, failed);
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		if (!requiresAuthentication(request, response)) {
			chain.doFilter(request, response);

			return;
		}
		Authentication authResult = null;
		try {
			authResult = attemptAuthentication(request, response);
			if (authResult == null) {
				return;
			}
		}
		catch(AuthenticationException failed){
//			unsuccessfulAuthentication(request,response,failed);
//			return;
			throw failed;
		}
		catch(Exception e){
			throw new RuntimeException(e);
		}
			successfulAuthentication(request, response, chain, authResult);
			chain.doFilter(request, response);
	}



	
	
	
}
