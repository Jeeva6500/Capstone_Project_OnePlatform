package com.natwest.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.natwest.exception.UserNameAlreadyExist;
import com.natwest.model.UserProfile;
import com.natwest.service.UserService;

@RestController
@RequestMapping("/api/user")
//@CrossOrigin
public class UserController {

	
	private UserService userServ;

	@Autowired
	public UserController(UserService userServ) {
		super();
		this.userServ = userServ;
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> saveUser(@RequestBody UserProfile userProfile) throws UserNameAlreadyExist {
		System.out.println(userProfile);
		UserProfile user1;
		try {
			user1 = userServ.addUser(userProfile);
			return new ResponseEntity<UserProfile>(user1, HttpStatus.CREATED);
		} catch (UserNameAlreadyExist e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	
	@GetMapping("/get")
	public ResponseEntity<List<UserProfile>> gerUserList() {
		List<UserProfile> users = userServ.userList();
		return new ResponseEntity<List<UserProfile>>(users, HttpStatus.OK);
	}
	
	
	@PutMapping("/update/{customerId}")
	public ResponseEntity<?> updateUser(@PathVariable String customerId, @RequestBody UserProfile userProfile) {
		UserProfile obj = userServ.getByUserName(customerId); //retrieve the user you want to update
		obj.setPassword(userProfile.getPassword());
		obj.setName(userProfile.getName());
		obj.setEmail(userProfile.getEmail());
		obj.setPhone(userProfile.getPhone());
		obj.setPan(userProfile.getPan());
		
		userServ.updateUser(obj);
		return new ResponseEntity<String>("User details updated successfully", HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/delete/{customerId}")
	public ResponseEntity<?> deleteUser(@PathVariable String customerId) {
		userServ.deleteUser(customerId);
		return new ResponseEntity<List<UserProfile>>(userServ.userList(), HttpStatus.OK);
	}
	
	
	@GetMapping("/get/{customerId}")
	public ResponseEntity<?> getTransaction(@PathVariable String customerId)  {
		return new ResponseEntity<UserProfile>(userServ.getByUserName(customerId), HttpStatus.OK);
	}

	
	
}
