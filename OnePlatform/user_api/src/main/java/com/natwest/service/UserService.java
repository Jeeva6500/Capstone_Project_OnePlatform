package com.natwest.service;

import java.util.List;

import com.natwest.exception.UserNameAlreadyExist;
import com.natwest.model.UserProfile;

import jakarta.mail.MessagingException;

public interface UserService {
	
	public UserProfile addUser(UserProfile userProfile) throws UserNameAlreadyExist;
	
	public UserProfile updateUser(UserProfile userProfile);
	
	public void deleteUser(String customerId);
	
	public List<UserProfile> userList();
	
	public UserProfile getByUserName(String customerId);
	

	

}
