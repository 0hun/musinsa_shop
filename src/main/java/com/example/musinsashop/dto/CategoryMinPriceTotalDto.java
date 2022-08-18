package com.example.musinsashop.dto;

import com.example.musinsashop.domain.Category;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryMinPriceTotalDto {

    private final List<CategoryMinPriceDto> categoryMinPriceDtoList;

    private final int totalPrice;

    @Builder
    public CategoryMinPriceTotalDto(List<CategoryMinPriceDto> categoryMinPriceDtoList,
            int totalPrice) {
        this.categoryMinPriceDtoList = categoryMinPriceDtoList;
        this.totalPrice = totalPrice;
    }

    public static CategoryMinPriceTotalDto of(List<Category> categories) {
        List<CategoryMinPriceDto> categoryMinPriceDtoList = categories.stream()
                .flatMap(c -> c.getBrands().stream())
                .map(CategoryMinPriceDto::of)
                .collect(Collectors.toList());

        int minTotalPrice = minTotalPrice(categoryMinPriceDtoList);

        return CategoryMinPriceTotalDto.builder()
                .categoryMinPriceDtoList(categoryMinPriceDtoList)
                .totalPrice(minTotalPrice)
                .build();
    }

    private static int minTotalPrice(List<CategoryMinPriceDto> categoryMinPriceDtoList) {
        return categoryMinPriceDtoList.stream()
                .mapToInt(CategoryMinPriceDto::getPrice)
                .sum();
    }
}
