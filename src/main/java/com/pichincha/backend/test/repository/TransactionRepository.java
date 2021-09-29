package com.pichincha.backend.test.repository;

import com.pichincha.backend.test.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("Select t FROM Transaction t LEFT JOIN fetch t.account a WHERE a.id = ?1")
    List<Transaction> findByAccount_Id(Long accountId);
}
