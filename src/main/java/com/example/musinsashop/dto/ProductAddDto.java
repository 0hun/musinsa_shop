package com.example.musinsashop.dto;

import com.example.musinsashop.domain.DataStatus;
import com.example.musinsashop.domain.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductAddDto {

    private Long categoryId;

    private Long BrandId;

    private Integer price;

    public ProductAddDto() {
    }

    @Builder
    public ProductAddDto(Long categoryId, Long brandId, Integer price) {
        this.categoryId = categoryId;
        BrandId = brandId;
        this.price = price;
    }

    public Product toEntity() {
        return Product.builder()
                .price(this.price)
                .status(DataStatus.DEFAULT)
                .build();
    }
}
