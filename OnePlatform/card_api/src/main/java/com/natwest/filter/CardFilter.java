package com.natwest.filter;

import java.io.IOException;

import org.springframework.http.HttpMethod;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.GenericFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CardFilter extends GenericFilter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		
		if(req.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name())) {
			chain.doFilter(req, res);
		}
		else {
			String authHeader = req.getHeader("Authorization");
			
			if(authHeader==null || !authHeader.startsWith("Bearer")) {
				throw new ServletException("Token missing or invalid");
			}
			String token = authHeader.substring(7);
			
			;
			try {
				
				JwtParser jparser = Jwts.parser().setSigningKey("mysecretkey");
				Jwt currentTokenObj = jparser.parse(token);
				Claims currentusrname = (Claims) currentTokenObj.getBody();
				
				//current logged in user
				req.setAttribute("currentuser", currentusrname.getSubject());
				req.setAttribute("token", token);
				
			} catch (SignatureException e) {
				throw new ServletException("Signature mismatch");
			} catch (MalformedJwtException me) {
				throw new ServletException("Token has been modified by unauthorized user");
			} catch(ExpiredJwtException ee) {
				throw new ServletException("Token expired");
			} catch (IllegalArgumentException le) {
				throw new ServletException("Check and relogin");
			}
			
			chain.doFilter(req, res);
		}
		
	}

}
