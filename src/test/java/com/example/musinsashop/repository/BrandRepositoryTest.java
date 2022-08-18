package com.example.musinsashop.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.musinsashop.domain.Brand;
import com.example.musinsashop.domain.Category;
import com.example.musinsashop.dto.BrandAddDto;
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
public class BrandRepositoryTest {

    @Autowired
    BrandRepository brandRepository;

    @Test
    @DisplayName("brand 저장 테스트")
    void saveBrand() {
        //given
        CategoryAddDto categoryAddDto = CategoryAddDto.builder()
                .name("상의")
                .build();

        Category category = categoryAddDto.toEntity();

        BrandAddDto brandAddDto = BrandAddDto.builder()
                .name("C")
                .build();

        Brand brand = brandAddDto.toEntity();
        brand.addCategory(category);

        //when
        brandRepository.save(brand);

        //then
        assertNotNull(brand.getCreatedAt());
    }

    @DisplayName("값이 일부 비어 있는 brand 객체 저장 테스트 - 객체 insert시 제약조건 위반 테스트")
    @Test
    void saveEmptyBrand() {
        //given
        BrandAddDto brandAddDto = BrandAddDto.builder()
                .build();

        Brand brand = brandAddDto.toEntity();

        //when
        Throwable thrown = catchThrowable(() -> {
            brandRepository.save(brand);
        });

        //then
        assertThat(thrown).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("brand 조회 테스트")
    void findBrand() {
        //given
        CategoryAddDto categoryAddDto = CategoryAddDto.builder()
                .name("상의")
                .build();

        Category category = categoryAddDto.toEntity();

        BrandAddDto brandAddDto = BrandAddDto.builder()
                .name("C")
                .build();

        Brand brand = brandAddDto.toEntity();
        brand.addCategory(category);
        brandRepository.save(brand);

        //when
        boolean existsBrand = brandRepository.existsByName(brandAddDto.getName());

        //then
        assertThat(existsBrand).isTrue();
    }

    @DisplayName("brand 존재 여부 실패 테스트")
    @Test
    void existsBrandFail() {
        //given
        String name = "C";

        //when
        boolean existsCategory = brandRepository.existsByName(name);

        //then
        assertThat(existsCategory).isFalse();
    }

    @Test
    @DisplayName("brand 삭제 테스트")
    void deleteBrand() {
        //given
        CategoryAddDto categoryAddDto = CategoryAddDto.builder()
                .name("상의")
                .build();

        Category category = categoryAddDto.toEntity();

        BrandAddDto brandAddDto = BrandAddDto.builder()
                .name("C")
                .build();

        Brand brand = brandAddDto.toEntity();
        brand.addCategory(category);
        brandRepository.save(brand);

        //when
        brand.delete();

        //then
        assertThat(brand.isDeleted()).isTrue();
    }

    @DisplayName("brand 삭제 실패 테스트")
    @Test
    void deleteBrandFail() {
        //given
        String name = "C";

        //when
        Optional<Brand> savedBrand = brandRepository.findByName(name);

        //then
        assertThat(savedBrand).isEqualTo(Optional.empty());
    }

}
