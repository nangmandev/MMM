package com.spring.mmm.domain.recommends.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "food_category")
@Entity
public class FoodCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_category_id")
    private Integer foodCategoryId;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", columnDefinition = "VARCHAR(255)")
    private FoodCategory name;

    @Column(name = "color")
    private String color;

    @OneToMany(mappedBy = "foodCategoryEntity")
    private List<FoodEntity> foods;
}
