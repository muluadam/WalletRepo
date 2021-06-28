package com.digital.wallet.repositories;

import com.digital.wallet.models.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Long> {

	Wallet findByWalletTag(String from);
	
//    @Query("SELECT w FROM Wallet  w WHERE w.walletId=:walletId")
//    Iterable<Wallet> findWalletById(@Param("walletId") Integer walletId);
}
