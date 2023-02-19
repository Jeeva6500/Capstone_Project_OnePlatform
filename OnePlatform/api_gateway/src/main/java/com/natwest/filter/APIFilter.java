package com.natwest.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.ServletException;
import reactor.core.publisher.Mono;

@Component
public class APIFilter implements GatewayFilter {

    @Value("${jwt.secretkey}")
    private String secretKey;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        if(!request.getHeaders().containsKey("Authorization")){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            System.out.println("Does not contain Authorization header");
            return response.setComplete();
        }

        String authHeader = request.getHeaders().getOrEmpty("Authorization").get(0);

        if (!authHeader.startsWith("Bearer ")){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            System.out.println("Does not contain Bearer");
            return response.setComplete();
        }

        String jwt_token = authHeader.substring(7);

        Claims claims = null;
        try {
            claims = getClaims(jwt_token);
            String email = claims.getSubject();
            request.mutate().header("email",email);
			//current logged in user
            
        } catch (Exception e) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            System.out.println("Does not contain Valid token");
            return response.setComplete();
        }
        return chain.filter(exchange);
    }

    private Claims getClaims(String jwtToken) throws ServletException {
    	try {
			
			JwtParser jparser = Jwts.parser().setSigningKey(secretKey);
			Jwt currentTokenObj = jparser.parse(jwtToken);
			Claims currentusrname = (Claims) currentTokenObj.getBody();
			
			return currentusrname;
			
			
		} catch (SignatureException e) {
			throw new ServletException("Signature mismatch");
		} catch (MalformedJwtException me) {
			throw new ServletException("Token has been modified by unauthorized user");
		} catch(ExpiredJwtException ee) {
			throw new ServletException("Token expired");
		} catch (IllegalArgumentException le) {
			throw new ServletException("Check and relogin");
		}
		
    }
}

