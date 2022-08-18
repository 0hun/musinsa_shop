package com.example.musinsashop.repository;

import com.example.musinsashop.domain.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByPrice(Integer price);

    Optional<Product> findByPrice(Integer price);
}
