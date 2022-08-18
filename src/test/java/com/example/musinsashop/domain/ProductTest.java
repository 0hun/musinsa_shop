package com.example.musinsashop.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.musinsashop.dto.BrandAddDto;
import com.example.musinsashop.dto.ProductAddDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProductTest {

    Brand brand;
    ProductAddDto productAddDto;

    @BeforeEach
    public void createProductDto() {
        BrandAddDto brandAddDto = BrandAddDto.builder()
                .name("C")
                .build();

        brand = brandAddDto.toEntity();

        productAddDto = ProductAddDto.builder()
                .price(1000)
                .build();
    }

    @DisplayName("productAddDto를 이용하여 product 객체를 생성 테스트")
    @Test
    void createProduct() {
        //given

        //when
        Product product = productAddDto.toEntity();

        //then
        assertThat(product.getPrice()).isEqualTo(productAddDto.getPrice());
    }

    @DisplayName("brand 연관관계 추가 후 product 객체가 같은 객체인지 테스트")
    @Test
    void productAddBrand() {
        //given

        //when
        Product product = productAddDto.toEntity();
        product.addBrand(brand);

        //then
        assertThat(brand.getProducts().get(0)).isEqualTo(product);
    }

}
