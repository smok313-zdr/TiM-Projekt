package com.example.shop.repositories;

import com.example.shop.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, String> {
    ProductEntity findProductById(String id);
}
