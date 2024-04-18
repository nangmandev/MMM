package com.spring.mmm.domain.mbtis.domain;

import com.spring.mmm.domain.recommends.domain.FoodMBTIEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "mbti")
@Entity
public class MukBTIEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mbti_id")
    private Integer mukbtiId;

    @Column(name = "mbti_name")
    @Enumerated(EnumType.STRING)
    private MukBTIType type;

    @OneToMany(mappedBy = "mukBTIEntity", cascade = CascadeType.REMOVE)
    private List<FoodMBTIEntity> foodMBTIEntities;

    @OneToMany(mappedBy = "mukBTIEntity", cascade = CascadeType.REMOVE)
    private List<MukBTIQuestionEntity> mukBTIQuestionEntities;

    @OneToMany(mappedBy = "mukBTIEntity", cascade = CascadeType.REMOVE)
    private List<MukBTIResultEntity> mukBTIResultEntities;
}
