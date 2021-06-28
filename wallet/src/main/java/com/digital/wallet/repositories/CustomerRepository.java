package com.digital.wallet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digital.wallet.models.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
		Customer findByEmail(String str);

//    @Query("SELECT c FROM Customer c WHERE c.email=:email")
//    Iterable<Customer> findCustomerByEmail(@Param("email") String email);

}
