package com.natwest.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.natwest.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	@Query("select a from Transaction a where a.cardNo=?1")
	public Optional<List<Transaction>> GetByCardNo(String cardNo);

}
