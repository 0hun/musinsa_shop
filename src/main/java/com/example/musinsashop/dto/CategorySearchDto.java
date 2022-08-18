package com.example.musinsashop.dto;

import com.example.musinsashop.domain.Brand;
import com.example.musinsashop.domain.Category;
import com.example.musinsashop.domain.DataStatus;
import java.util.List;
import lombok.Builder;
import lombok.Getter;


@Getter
public class CategorySearchDto {

    private final long id;

    private final String name;

    private final Category category;

    private final List<Brand> brands;

    private final DataStatus status;

    @Builder
    public CategorySearchDto(Long id, String name, Category category, List<Brand> brands,
            DataStatus status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.brands = brands;
        this.status = status;
    }

    public static CategorySearchDto of(Category category) {
        return CategorySearchDto.builder()
                .id(category.getId())
                .name(category.getName())
                .brands(category.getBrands())
                .status(category.getStatus())
                .build();
    }
}
