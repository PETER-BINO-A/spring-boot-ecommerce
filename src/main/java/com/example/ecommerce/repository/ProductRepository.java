package com.example.ecommerce.repository;

import com.example.ecommerce.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

}
