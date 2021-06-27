package com.digital.wallet.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.digital.wallet.models.Transaction;

public interface TansactionRepository extends CrudRepository<Transaction, Long> {
//    @Query("SELECT t FROM BankTransaction t WHERE t.transactionId=:transactionId")
//    Iterable<Transaction> findBankTransactionById(@Param("transactionId") Integer transactionId);
}
