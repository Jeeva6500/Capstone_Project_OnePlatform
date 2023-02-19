package com.natwest.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.natwest.email.EmailSenderService;
import com.natwest.exception.UserNameAlreadyExist;
import com.natwest.model.UserProfile;
import com.natwest.repo.UserRepository;

import jakarta.mail.MessagingException;

@Service
public class UserServiceImpl implements UserService{
	

		
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private EmailSenderService senderService;
	
	
	
	@Override
	public UserProfile addUser(UserProfile userProfile) throws UserNameAlreadyExist {
		
		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
		String encryptPassword = bcryptPasswordEncoder.encode(userProfile.getPassword());
		java.util.Optional<UserProfile> optUser = userRepo.findById(userProfile.getPhone());
		if (optUser.isPresent()) {
			throw new UserNameAlreadyExist("Mobile number already registered!");
		}
		else {
			userProfile.setCustomerId(UUID.randomUUID().toString().substring(0, 8));
			userProfile.setPassword(encryptPassword);
			UserProfile temp = userRepo.save(userProfile);
			senderService.sendSimpleEmail(userProfile.getEmail(), "Confidential: PayPro Customer-Id generated", "Dear Customer, Thanks for registering with PayPro. Your Customer Id is:"+userProfile.getCustomerId());
			System.out.println("Email Sent successfully...");
			return temp;
			
		}
	}

	@Override
	public UserProfile updateUser(UserProfile userProfile) {
		UserProfile temp = userRepo.save(userProfile);
		return temp;
	}

	@Override
	public void deleteUser(String customerId) {
		userRepo.deleteById(customerId);
		
	}

	@Override
	public List<UserProfile> userList() {
		List<UserProfile> userList = (List<UserProfile>) userRepo.findAll();
		return userList;
	}

	@Override
	public UserProfile getByUserName(String customerId) {
		// TODO Auto-generated method stub
		return userRepo.findById(customerId).get();
	}

	
	}


