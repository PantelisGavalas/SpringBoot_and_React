package com.pantelisgavalas.lil.wisdom_pet.data.repositories;

import com.pantelisgavalas.lil.wisdom_pet.data.entities.ServiceEntity;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<ServiceEntity, Long> {
}
