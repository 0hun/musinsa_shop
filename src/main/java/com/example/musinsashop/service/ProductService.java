package com.example.musinsashop.service;

import com.example.musinsashop.domain.Brand;
import com.example.musinsashop.domain.Product;
import com.example.musinsashop.dto.ProductAddDto;
import com.example.musinsashop.dto.ProductUpdateDto;
import com.example.musinsashop.repository.BrandRepository;
import com.example.musinsashop.repository.ProductRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;


    @Transactional
    public void addProduct(ProductAddDto dto) {
        Brand brand = brandRepository.findById(dto.getBrandId())
                .orElseThrow(NoSuchElementException::new);

        Product product = dto.toEntity();
        product.addBrand(brand);

        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(ProductUpdateDto dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(NoSuchElementException::new);

        product.updatePrice(dto.getPrice());
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        product.delete();
    }
}
