package com.example.musinsashop.service;

import com.example.musinsashop.domain.Category;
import com.example.musinsashop.dto.CategoryMinPriceTotalDto;
import com.example.musinsashop.dto.CategorySearchDto;
import com.example.musinsashop.repository.CategoryRepository;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategorySearchDto findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        return CategorySearchDto.of(category);
    }

    @Cacheable("minPrice")
    public CategoryMinPriceTotalDto findMinPrice() {
        List<Category> categories = categoryRepository.findAll();

        return CategoryMinPriceTotalDto.of(categories);
    }
}
