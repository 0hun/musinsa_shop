package com.example.musinsashop.domain;

import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "product")
public class Product extends BaseTimeEntity {

    // 아이디(pk)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 가격
    @Column(name = "price", nullable = false)
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    // 회원 상태 DEFAULT(기본), DELETED(삭제됨)
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private DataStatus status;

    protected Product() {
    }

    @Builder
    public Product(Long id, Integer price, Brand brand, DataStatus status) {
        this.id = id;
        this.price = price;
        this.brand = brand;
        this.status = status;
    }

    public void addBrand(Brand brand) {
        if (this.brand != null) {
            this.brand.removeProduct(this);
        }

        this.brand = brand;
        brand.addProduct(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(price,
                product.price) && Objects.equals(brand, product.brand)
                && status == product.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, brand, status);
    }

    public void delete() {
        this.status = DataStatus.DELETED;
    }

    public boolean isDeleted() {
        if (this.status.equals(DataStatus.DELETED)) {
            return true;
        }

        return false;
    }
}
