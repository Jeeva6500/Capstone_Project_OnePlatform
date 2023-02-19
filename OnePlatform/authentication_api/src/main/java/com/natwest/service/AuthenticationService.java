package com.natwest.service;

import java.util.Optional;

import com.natwest.model.UserProfile;

public interface AuthenticationService {
	public Optional<UserProfile> findById(String customerId);

}
