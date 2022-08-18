package com.example.musinsashop.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "category")
public class Category extends BaseTimeEntity {

    // 아이디(pk)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 이름
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    private List<Brand> brands = new ArrayList<>();

    // 회원 상태 DEFAULT(기본), DELETED(삭제됨)
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private DataStatus status;

    protected Category() {
    }

    @Builder
    public Category(Long id, String name, List<Brand> brands, DataStatus status) {
        this.id = id;
        this.name = name;
        this.brands = brands;
        this.status = status;
    }

    public void addBrand(Brand brand) {
        if (brands == null) {
            this.brands = new ArrayList<>();
        }
        this.brands.add(brand);
    }

    public void removeBrand(Brand brand) {
        this.brands.remove(brand);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(name,
                category.name) && Objects.equals(brands, category.brands)
                && status == category.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brands, status);
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
