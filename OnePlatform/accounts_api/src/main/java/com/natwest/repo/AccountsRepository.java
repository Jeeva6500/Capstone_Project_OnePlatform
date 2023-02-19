package com.natwest.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.natwest.model.Accounts;

@Repository
@Transactional
public interface AccountsRepository extends CrudRepository<Accounts, String>{
	
	
public List<Accounts> findByAccountTypeAndCustomerId(String accountType, String customerId); //select * from accounts where accountType=? and customerId=?
	
	@Query("select a from Accounts a where a.customerId=?1")
	public List<Accounts> getByCustomerId(String customerId);
	
	public List<Accounts> findAll(Sort sort);
	
	@Modifying
	@Query("delete from Accounts a where a.customerId=?1")
	public void deleteByCustomerId(String customerId);


	
	
}
