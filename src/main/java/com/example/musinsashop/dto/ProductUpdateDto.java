package com.example.musinsashop.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProductUpdateDto {

    @NotNull
    private final Long productId;

    @NotNull
    private final Integer price;

    public ProductUpdateDto(Long productId, Integer price) {
        this.productId = productId;
        this.price = price;
    }
}
