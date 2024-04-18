package com.spring.mmm.domain.mukgroups.domain;

import com.spring.mmm.common.event.Events;
import com.spring.mmm.domain.mukgroups.controller.response.MukgroupResponse;
import com.spring.mmm.domain.mukgroups.event.MukgroupMukjukSelectedEvent;
import com.spring.mmm.domain.mukgroups.exception.MukGroupErrorCode;
import com.spring.mmm.domain.mukgroups.exception.MukGroupException;
import com.spring.mmm.domain.mukjuks.domain.MukgroupMukjukEntity;
import com.spring.mmm.domain.mukjuks.domain.MukjukEntity;
import com.spring.mmm.domain.muklogs.domain.MuklogEntity;
import com.spring.mmm.domain.recommends.domain.FoodRecommendEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "mukgroup")
@Entity
public class MukgroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mukgroup_id")
    private Long mukgroupId;

    @Column(name = "name")
    private String name;

    @Column(name = "image_src")
    private String imageSrc;

    @Column(name = "is_solo")
    private Boolean isSolo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mukjuk_id")
    private MukjukEntity mukjukEntity;

    @OneToMany(mappedBy = "mukgroupEntity", cascade = CascadeType.REMOVE)
    private List<MukboEntity> mukboEntities;

    @OneToMany(mappedBy = "mukgroupEntity", cascade = CascadeType.REMOVE)
    private List<MuklogEntity> mukLogEntities;

    @OneToMany(mappedBy = "mukgroupEntity", cascade = CascadeType.REMOVE)
    private List<MukgroupMukjukEntity> mukgroupMukJukEntities;

    @OneToMany(mappedBy = "mukgroupEntity", cascade = CascadeType.REMOVE)
    private List<FoodRecommendEntity> foodRecommendEntities;

    public static MukgroupEntity create(String name, Boolean isSolo){
        return MukgroupEntity.builder()
                .name(name)
                .isSolo(isSolo)
                .build();
    }

    public static MukgroupEntity createWithOnlyId(Long mukgroupId){
        return MukgroupEntity.builder()
                .mukgroupId(mukgroupId)
                .build();
    }

    public MukgroupEntity modifyMukgroupName(String name){
        return MukgroupEntity.builder()
                .mukgroupId(this.mukgroupId)
                .name(name)
                .isSolo(this.isSolo)
                .imageSrc(this.imageSrc)
                .build();
    }

    public MukgroupEntity modifyMukgroupImage(String ImageSrc){
        return MukgroupEntity.builder()
                .mukgroupId(this.mukgroupId)
                .name(this.name)
                .isSolo(this.isSolo)
                .imageSrc(ImageSrc)
                .build();
    }

    public void modifyRepMukjuk(Long badgeId) {
        for (MukgroupMukjukEntity mukgroupMukJuk : mukgroupMukJukEntities) {
            MukjukEntity groupMukjuk = mukgroupMukJuk.getMukjukEntity();
            if (groupMukjuk.getMukjukId().equals(badgeId)) {
                mukjukEntity = groupMukjuk;
                Events.raise(new MukgroupMukjukSelectedEvent(mukjukEntity.getName(), this.mukgroupId));
                return;
            }
        }
        throw new MukGroupException(MukGroupErrorCode.SELECTED_NOT_ACHIEVE_MUKJUK);
    }

    @Override
    public String toString() {
        return "MukgroupEntity{" +
                "mukgroupId=" + mukgroupId +
                ", name='" + name + '\'' +
                ", imageSrc='" + imageSrc + '\'' +
                ", isSolo=" + isSolo +
                '}';
    }

}
