package com.example.musinsashop.dto;

import com.example.musinsashop.domain.Brand;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryMinPriceDto {

    private final String categoryName;

    private final String brandName;

    private final int price;

    @Builder
    public CategoryMinPriceDto(String categoryName, String brandName, int price) {
        this.categoryName = categoryName;
        this.brandName = brandName;
        this.price = price;
    }

    public static CategoryMinPriceDto of(Brand brand) {
        return CategoryMinPriceDto.builder()
                .categoryName(brand.getCategory().getName())
                .brandName(brand.getName())
                .price(brand.minPrice())
                .build();
    }
}
