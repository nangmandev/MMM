package com.spring.mmm.domain.mukjuks.domain;

import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukjuks.domain.MukjukEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "mukgroup_mukjuk")
@Entity
public class MukgroupMukjukEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mukgroup_mukjuk_id")
    private Long mukgorupMukjukId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mukjuk_id")
    private MukjukEntity mukjukEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mukgroup_id")
    private MukgroupEntity mukgroupEntity;

    public static MukgroupMukjukEntity create(MukgroupEntity mukgroupEntity, MukjukEntity mukjukEntity){
        return MukgroupMukjukEntity.builder()
                .mukjukEntity(mukjukEntity)
                .mukgroupEntity(mukgroupEntity)
                .build();
    }
    public static MukgroupMukjukEntity create(MukgroupEntity mukgroupEntity, Long mukjukId){
        return MukgroupMukjukEntity.builder()
                .mukjukEntity(MukjukEntity.builder()
                        .mukjukId(mukjukId)
                        .build())
                .mukgroupEntity(mukgroupEntity)
                .build();
    }

    @Override
    public String toString() {
        return "MukgroupMukjukEntity{" +
                "mukgorupMukjukId=" + mukgorupMukjukId +
                ", mukgroupEntity=" + mukgroupEntity +
                '}';
    }
}
