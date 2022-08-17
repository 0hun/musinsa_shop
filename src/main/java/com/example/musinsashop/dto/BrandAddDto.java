package com.example.musinsashop.dto;

import com.example.musinsashop.domain.Brand;
import com.example.musinsashop.domain.DataStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BrandAddDto {

    private Long categoryId;

    private String name;

    public BrandAddDto() {
    }

    @Builder
    public BrandAddDto(long categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public Brand toEntity() {
        return Brand.builder()
                .name(this.name)
                .status(DataStatus.DEFAULT)
                .build();
    }
}
