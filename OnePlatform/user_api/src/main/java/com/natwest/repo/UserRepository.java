package com.natwest.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.natwest.model.UserProfile;

@Repository
public interface UserRepository extends CrudRepository<UserProfile, String>{

}
