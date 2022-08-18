package com.example.musinsashop.controller;

import com.example.musinsashop.dto.CategoryMinPriceTotalDto;
import com.example.musinsashop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/categories")
@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/min-price")
    public ResponseEntity<CategoryMinPriceTotalDto> findMinPrice() {
        CategoryMinPriceTotalDto dto = categoryService.findMinPrice();

        return ResponseEntity.ok(dto);
    }

}
