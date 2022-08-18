package com.example.musinsashop.dto;

import com.example.musinsashop.domain.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductAddDto {

    private long categoryId;

    private long BrandId;

    private int price;

    public ProductAddDto() {
    }

    @Builder
    public ProductAddDto(long categoryId, long brandId, int price) {
        this.categoryId = categoryId;
        BrandId = brandId;
        this.price = price;
    }

    public Product toEntity() {
        return Product.builder()
                .price(this.price)
                .build();
    }
}
