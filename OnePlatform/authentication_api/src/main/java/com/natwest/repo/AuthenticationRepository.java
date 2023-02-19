package com.natwest.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.natwest.model.UserProfile;

@Repository
public interface AuthenticationRepository extends CrudRepository<UserProfile, String>{
	public UserProfile findByCustomerIdAndPassword(String customerId, String password);
	

}
