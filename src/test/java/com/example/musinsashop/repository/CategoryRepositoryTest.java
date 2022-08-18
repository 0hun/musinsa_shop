package com.example.musinsashop.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.musinsashop.domain.Category;
import com.example.musinsashop.dto.CategoryAddDto;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("category 저장 테스트")
    void saveCategory() {
        //given
        CategoryAddDto categoryAddDto = CategoryAddDto.builder()
                .name("상의")
                .build();

        Category category = categoryAddDto.toEntity();

        //when
        categoryRepository.save(category);

        //then
        assertNotNull(category.getCreatedAt());
    }

    @DisplayName("값이 일부 비어 있는 category 객체 저장 테스트 - 객체 insert시 제약조건 위반 테스트")
    @Test
    void saveEmptyCategory() {
        //given
        CategoryAddDto categoryAddDto = CategoryAddDto.builder()
                .build();

        Category category = categoryAddDto.toEntity();

        //when
        Throwable thrown = catchThrowable(() -> {
            categoryRepository.save(category);
        });

        //then
        assertThat(thrown).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("category 조회 테스트")
    void findCategory() {
        //given
        CategoryAddDto categoryAddDto = CategoryAddDto.builder()
                .name("상의")
                .build();

        Category category = categoryAddDto.toEntity();

        categoryRepository.save(category);

        //when
        boolean existsCategory = categoryRepository.existsByName(categoryAddDto.getName());

        //then
        assertThat(existsCategory).isTrue();
    }

    @DisplayName("category 존재 여부 실패 테스트")
    @Test
    void existsCategoryFail() {
        //given
        String name = "상의";

        //when
        boolean existsCategory = categoryRepository.existsByName(name);

        //then
        assertThat(existsCategory).isFalse();
    }

    @Test
    @DisplayName("category 삭제 테스트")
    void deleteCategory() {
        //given
        CategoryAddDto categoryAddDto = CategoryAddDto.builder()
                .name("상의")
                .build();

        Category category = categoryAddDto.toEntity();

        categoryRepository.save(category);

        //when
        category.delete();

        //then
        assertThat(category.isDeleted()).isTrue();
    }

    @DisplayName("category 삭제 실패 테스트")
    @Test
    void deleteCategoryFail() {
        //given
        String name = "상의";

        //when
        Optional<Category> savedCategory = categoryRepository.findByName(name);

        //then
        assertThat(savedCategory).isEqualTo(Optional.empty());
    }

}
