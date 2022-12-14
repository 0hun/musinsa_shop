package com.example.musinsashop.dto;

import com.example.musinsashop.domain.Category;
import com.example.musinsashop.domain.DataStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryAddDto {
    private String name;

    public CategoryAddDto() {
    }

    @Builder
    public CategoryAddDto(String name) {
        this.name = name;
    }

    public Category toEntity(){
        return Category.builder()
                .name(this.name)
                .status(DataStatus.DEFAULT)
                .build();
    }
}
