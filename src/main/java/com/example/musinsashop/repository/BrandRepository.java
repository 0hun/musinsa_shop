package com.example.musinsashop.repository;

import com.example.musinsashop.domain.Brand;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    boolean existsByName(String name);

    Optional<Brand> findByName(String name);
}
