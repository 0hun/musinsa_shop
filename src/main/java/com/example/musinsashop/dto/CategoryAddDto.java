package com.example.musinsashop.dto;

import com.example.musinsashop.domain.Category;
import com.example.musinsashop.domain.DataStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryAddDto {

    private String name;

    private DataStatus status;

    public CategoryAddDto() {
    }

    @Builder
    public CategoryAddDto(String name, DataStatus status) {
        this.name = name;
        this.status = status;
    }

    public Category toEntity(){
        return Category.builder()
                .name(this.name)
                .status(DataStatus.DEFAULT)
                .build();
    }
}
