package com.example.musinsashop.dto;

import com.example.musinsashop.domain.DataStatus;
import com.example.musinsashop.domain.Product;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductAddDto {

    @NotNull
    private Long BrandId;

    @NotNull
    private Integer price;

    public ProductAddDto() {
    }

    @Builder
    public ProductAddDto(Long brandId, Integer price) {
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
