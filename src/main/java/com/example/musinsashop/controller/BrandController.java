package com.example.musinsashop.controller;

import com.example.musinsashop.dto.BrandMinPriceDto;
import com.example.musinsashop.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/brands")
@RequiredArgsConstructor
@RestController
public class BrandController {

    private final BrandService brandService;

    @GetMapping("/min-price")
    public ResponseEntity<BrandMinPriceDto> findMinPrice() {
        BrandMinPriceDto dto = brandService.findMinPrice();

        return ResponseEntity.ok(dto);
    }

}
