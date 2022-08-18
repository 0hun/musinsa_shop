package com.example.musinsashop.dto;

import com.example.musinsashop.domain.Category;
import com.example.musinsashop.domain.Product;
import java.util.Comparator;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BrandMaxPriceDto {

    private String brandName;

    private long maxPrice;

    public BrandMaxPriceDto() {
    }

    @Builder
    public BrandMaxPriceDto(String brandName, long maxPrice) {
        this.brandName = brandName;
        this.maxPrice = maxPrice;
    }

    public static BrandMaxPriceDto of(Product product) {
        return BrandMaxPriceDto.builder()
                .brandName(product.getBrand().getName())
                .maxPrice(product.getPrice())
                .build();
    }

    public static BrandMaxPriceDto of(Category category) {
        return category.getBrands().stream()
                .flatMap(b -> b.getProducts().stream())
                .max(Comparator.comparing(Product::getPrice))
                .map(BrandMaxPriceDto::of)
                .orElse(new BrandMaxPriceDto());
    }
}
