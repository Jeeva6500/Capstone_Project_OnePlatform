package com.natwest.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.natwest.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, String>{
	
	public Optional<List<Card>> findByAccountNo(String accountNo);

}
