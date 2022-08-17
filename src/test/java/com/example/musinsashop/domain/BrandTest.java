package com.example.musinsashop.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.musinsashop.dto.BrandAddDto;
import com.example.musinsashop.dto.CategoryAddDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BrandTest {

    BrandAddDto brandAddDto;
    Category category;

    @BeforeEach
    public void createBrandDto() {
        CategoryAddDto categoryAddDto = CategoryAddDto.builder()
                .name("상의")
                .build();

        category = categoryAddDto.toEntity();

        brandAddDto = BrandAddDto.builder()
                .name("C")
                .build();
    }

    @DisplayName("brandAddDto를 이용하여 brand 객체를 생성 테스트")
    @Test
    void brandCategory() {
        //given
        BrandAddDto brandAddDto = BrandAddDto.builder()
                .name("C")
                .build();

        //when
        Brand brand = brandAddDto.toEntity();

        //then
        assertThat(brand.getName()).isEqualTo(brandAddDto.getName());
    }


    @DisplayName("category 연관관계 추가 후 brand 객체가 같은 객체인지 테스트")
    @Test
    void brandAddCategory() {
        //given
        BrandAddDto brandAddDto = BrandAddDto.builder()
                .name("C")
                .build();

        //when
        Brand brand = brandAddDto.toEntity();
        category.addBrand(brand);
        brand.addCategory(category);

        //then
        assertThat(category.getBrands().get(0)).isEqualTo(brand);
    }

}
