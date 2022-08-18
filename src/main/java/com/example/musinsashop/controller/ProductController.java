package com.example.musinsashop.controller;

import com.example.musinsashop.dto.ProductAddDto;
import com.example.musinsashop.dto.ProductUpdateDto;
import com.example.musinsashop.service.ProductService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/products")
@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductAddDto dto) {
        productService.addProduct(dto);

        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<Void> updateProduct(@Valid @RequestBody ProductUpdateDto dto) {
        productService.updateProduct(dto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }

}
