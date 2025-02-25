package com.pantelisgavalas.lil.wisdom_pet.data.repositories;

import com.pantelisgavalas.lil.wisdom_pet.data.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
}
