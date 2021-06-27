package com.digital.wallet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.digital.wallet.models.Customer;



public interface CustomerRepository extends JpaRepository<Customer, Long> {
		Customer findByEmail(String str);

//    @Query("SELECT c FROM Customer c WHERE c.email=:email")
//    Iterable<Customer> findCustomerByEmail(@Param("email") String email);

}
