package com.natwest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natwest.model.UserProfile;
import com.natwest.repo.AuthenticationRepository;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

	@Autowired
	AuthenticationRepository authenticationRepo;
	
	@Override
	public Optional<UserProfile> findById(String customerId) {
		return authenticationRepo.findById(customerId);
	}

}
