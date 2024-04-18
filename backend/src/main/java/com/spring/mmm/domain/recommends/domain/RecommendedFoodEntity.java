package com.spring.mmm.domain.recommends.domain;

import com.spring.mmm.common.event.Events;
import com.spring.mmm.domain.mukus.event.RecommendedFoodEatenEvent;
import com.spring.mmm.domain.recommends.controller.request.RecommendedFoodRequest;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "recommended_food")
@Entity
public class RecommendedFoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommended_food_id")
    private Long recommendedFoodId;

    @Column(name = "eaten")
    private Boolean eaten;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private RecommendCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private FoodEntity foodEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_recommend")
    private FoodRecommendEntity foodRecommendEntity;

    public static RecommendedFoodEntity create(FoodEntity food,
                                               RecommendCategory category,
                                               FoodRecommendEntity foodRecommendEntity) {
        return RecommendedFoodEntity.builder()
                .eaten(false)
                .category(category)
                .foodEntity(food)
                .foodRecommendEntity(foodRecommendEntity)
                .build();

    }

    public void eat() {
        this.eaten = true;
        Events.raise(new RecommendedFoodEatenEvent(this.recommendedFoodId));
    }

}
