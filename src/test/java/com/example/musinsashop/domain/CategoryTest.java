package com.example.musinsashop.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.musinsashop.dto.CategoryAddDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @DisplayName("categoryAddDto를 이용하여 category 객체를 생성 테스트")
    @Test
    void createCategory() {
        //given
        CategoryAddDto categoryAddDto = CategoryAddDto.builder()
                .name("상의")
                .build();

        //when
        Category category = categoryAddDto.toEntity();

        //then
        assertThat(category.getName()).isEqualTo(categoryAddDto.getName());
    }

}
