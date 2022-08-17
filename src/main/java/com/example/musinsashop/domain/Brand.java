package com.example.musinsashop.domain;

import java.util.Objects;
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
@Table(name = "brand")
public class Brand extends BaseTimeEntity {

    // 아이디(pk)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 이름
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    // 회원 상태 DEFAULT(기본), DELETED(삭제됨)
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private DataStatus status;

    protected Brand() {
    }

    @Builder
    public Brand(Long id, String name, Category category, DataStatus status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.status = status;
    }

    public void addCategory(Category category) {
        if (this.category != null) {
            this.category.removeBrand(this);
        }

        this.category = category;
        category.addBrand(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Brand brand = (Brand) o;
        return Objects.equals(id, brand.id) && Objects.equals(name, brand.name)
                && Objects.equals(category, brand.category) && status == brand.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, status);
    }
}
