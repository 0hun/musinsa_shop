package com.example.musinsashop.service;

import com.example.musinsashop.domain.Brand;
import com.example.musinsashop.domain.Product;
import com.example.musinsashop.dto.BrandMinPriceDto;
import com.example.musinsashop.repository.BrandRepository;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandMinPriceDto findMinPrice() {
        List<Brand> brands = brandRepository.findAll();

        return brands.stream()
                .flatMap(b -> b.getProducts().stream())
                .min(Comparator.comparing(Product::getPrice))
                .map(BrandMinPriceDto::of)
                .orElseThrow(NoSuchElementException::new);
    }
}
