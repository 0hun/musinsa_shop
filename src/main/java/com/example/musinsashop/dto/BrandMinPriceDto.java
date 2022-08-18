package com.example.musinsashop.dto;

import com.example.musinsashop.domain.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BrandMinPriceDto {

    private final String brandName;

    private final long minPrice;

    @Builder
    public BrandMinPriceDto(String brandName, long minPrice) {
        this.brandName = brandName;
        this.minPrice = minPrice;
    }

    public static BrandMinPriceDto of(Product product) {
        return BrandMinPriceDto.builder()
                .brandName(product.getBrand().getName())
                .minPrice(product.getPrice())
                .build();
    }
}
