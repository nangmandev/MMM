package com.spring.mmm.domain.recommends.domain;

import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "eaten_mukbo")
@Entity
public class EatenMukboEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eaten_mukbo_id")
    private Long eatenMukboId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mukbo_id")
    private MukboEntity mukboEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_recommend_id")
    private FoodRecommendEntity foodRecommendEntity;

    public static EatenMukboEntity create(MukboEntity mukboEntity, FoodRecommendEntity foodRecommendEntity) {
        return EatenMukboEntity.builder()
                .mukboEntity(mukboEntity)
                .foodRecommendEntity(foodRecommendEntity)
                .build();
    }
}
