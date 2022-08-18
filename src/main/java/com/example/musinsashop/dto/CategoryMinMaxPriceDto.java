package com.example.musinsashop.dto;

import com.example.musinsashop.domain.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryMinMaxPriceDto {

    private final BrandMinPriceDto brandMinPriceDto;

    private final BrandMaxPriceDto brandMaxPriceDto;

    @Builder
    public CategoryMinMaxPriceDto(BrandMinPriceDto brandMinPriceDto,
            BrandMaxPriceDto brandMaxPriceDto) {
        this.brandMinPriceDto = brandMinPriceDto;
        this.brandMaxPriceDto = brandMaxPriceDto;
    }

    public static CategoryMinMaxPriceDto of(Category category) {
        return CategoryMinMaxPriceDto.builder()
                .brandMinPriceDto(BrandMinPriceDto.of(category))
                .brandMaxPriceDto(BrandMaxPriceDto.of(category))
                .build();
    }
}
