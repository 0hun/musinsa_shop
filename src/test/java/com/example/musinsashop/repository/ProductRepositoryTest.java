package com.example.musinsashop.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.musinsashop.domain.Brand;
import com.example.musinsashop.domain.Category;
import com.example.musinsashop.domain.Product;
import com.example.musinsashop.dto.BrandAddDto;
import com.example.musinsashop.dto.CategoryAddDto;
import com.example.musinsashop.dto.ProductAddDto;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("product 저장 테스트")
    void saveProduct() {
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

        ProductAddDto productAddDto = ProductAddDto.builder()
                .price(1000)
                .build();

        Product product = productAddDto.toEntity();
        product.addBrand(brand);

        //when
        productRepository.save(product);

        //then
        assertNotNull(product.getCreatedAt());
    }

    @Test
    @DisplayName("값이 일부 비어 있는 product 객체 저장 테스트 - 객체 insert시 제약조건 위반 테스트")
    void saveEmptyProduct() {
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

        ProductAddDto productAddDto = ProductAddDto.builder()
                .build();

        Product product = productAddDto.toEntity();
        product.addBrand(brand);

        //when
        Throwable thrown = catchThrowable(() -> {
            productRepository.save(product);
        });

        //then
        assertThat(thrown).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("product 조회 테스트")
    void findProduct() {
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

        ProductAddDto productAddDto = ProductAddDto.builder()
                .price(1000)
                .build();

        Product product = productAddDto.toEntity();
        product.addBrand(brand);

        productRepository.save(product);

        //when
        boolean existsBrand = productRepository.existsByPrice(productAddDto.getPrice());

        //then
        assertThat(existsBrand).isTrue();
    }

    @DisplayName("product 존재 여부 실패 테스트")
    @Test
    void existsProductFail() {
        //given
        int price = 1000;

        //when
        boolean existsCategory = productRepository.existsByPrice(price);

        //then
        assertThat(existsCategory).isFalse();
    }

    @Test
    @DisplayName("product 삭제 테스트")
    void deleteProduct() {
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

        ProductAddDto productAddDto = ProductAddDto.builder()
                .price(1000)
                .build();

        Product product = productAddDto.toEntity();
        product.addBrand(brand);

        productRepository.save(product);

        //when
        product.delete();

        //then
        assertThat(product.isDeleted()).isTrue();
    }

    @DisplayName("product 삭제 실패 테스트")
    @Test
    void deleteProductFail() {
        //given
        int price = 1000;

        //when
        Optional<Product> savedProduct = productRepository.findByPrice(price);

        //then
        assertThat(savedProduct).isEqualTo(Optional.empty());
    }

}
