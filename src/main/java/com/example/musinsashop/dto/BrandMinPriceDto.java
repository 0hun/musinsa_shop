package com.example.musinsashop.dto;

import com.example.musinsashop.domain.Category;
import com.example.musinsashop.domain.Product;
import java.util.Comparator;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BrandMinPriceDto {

    private String brandName;

    private long minPrice;

    public BrandMinPriceDto() {
    }

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

    public static BrandMinPriceDto of(Category category) {
        return category.getBrands().stream()
                .flatMap(b -> b.getProducts().stream())
                .min(Comparator.comparing(Product::getPrice))
                .map(BrandMinPriceDto::of)
                .orElse(new BrandMinPriceDto());
    }
}
