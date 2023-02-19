package com.natwest.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.natwest.model.UserProfile;
import com.natwest.service.AuthenticationService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;

@RestController
@EnableDiscoveryClient
public class AuthenticationController {
	
	 //@Value("${jwt.secretkey}")
	//    private String secretKey;

	@Autowired
	private AuthenticationService authenticationService;
	Map<String, String> map = new HashMap<>();
	
	static final long EXPIRYTIME = 1000*60*60;
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticate (@RequestBody UserProfile customer){
		
		String mytoken;
		try {
			mytoken = generateToken(customer);
			map.clear();
			map.put("token", mytoken);
			map.put("customerId", customer.getCustomerId());
			
			
			return new ResponseEntity<Map<String,String>>(map,HttpStatus.ACCEPTED);
			
		} catch (ServletException e) {
			map.clear();
			map.put("token", null);
			map.put("message", e.getMessage());

			return new ResponseEntity<Map<String,String>>(map,HttpStatus.UNAUTHORIZED);
		}
	    }

	
	
	public String generateToken(UserProfile credentials) throws ServletException {
    	 BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    	 if(credentials.getCustomerId()==null || credentials.getPassword()==null) { 
			throw new ServletException("Email and passwword cannot be blank");
		} else {
			Optional<UserProfile> temp = authenticationService.findById(credentials.getCustomerId());

			if (temp.isEmpty()) {
			throw new ServletException("Invalid Credentails, Enter correct details.");
		} else {
			UserProfile customer = temp.get();
		    if(!(bCryptPasswordEncoder.matches(credentials.getPassword(), customer.getPassword()))) {
		     	throw new ServletException("password is incorrect, please check again");
			}else{String jwtToken = Jwts
				             .builder()
				             .setSubject(credentials.getName())
				             .setIssuedAt(new Date())
				             .setExpiration(new Date(System.currentTimeMillis()+EXPIRYTIME))
				             .signWith(SignatureAlgorithm.HS256, "mysecretkey")
				             .compact();
		
		
		return jwtToken;
		
	}
		
	

	}
		}
	}
}


	



