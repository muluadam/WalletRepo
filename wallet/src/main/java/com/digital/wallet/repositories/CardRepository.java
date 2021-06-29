package com.digital.wallet.repositories;

import org.springframework.data.repository.CrudRepository;

import com.digital.wallet.models.Card;


public interface CardRepository extends CrudRepository<Card,Long> {

}
