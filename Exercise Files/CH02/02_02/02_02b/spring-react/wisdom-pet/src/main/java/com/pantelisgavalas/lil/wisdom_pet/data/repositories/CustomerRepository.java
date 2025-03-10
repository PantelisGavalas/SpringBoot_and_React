package com.pantelisgavalas.lil.wisdom_pet.data.repositories;

import com.pantelisgavalas.lil.wisdom_pet.data.entities.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

    CustomerEntity findByEmail(String email);
}
