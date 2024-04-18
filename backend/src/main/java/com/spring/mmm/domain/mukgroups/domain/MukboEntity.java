package com.spring.mmm.domain.mukgroups.domain;

import com.spring.mmm.domain.mbtis.domain.MBTI;
import com.spring.mmm.domain.mbtis.domain.MukBTIResultEntity;
import com.spring.mmm.domain.mukgroups.controller.response.MukboResponse;
import com.spring.mmm.domain.recommends.domain.EatenMukboEntity;
import com.spring.mmm.domain.users.infra.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "mukbo")
@Entity
public class MukboEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mukbo_id")
    private Long mukboId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private MukboType type;

    @OneToMany(mappedBy = "mukboEntity",  cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<MukBTIResultEntity> mukBTIResultEntities;

    @OneToMany(mappedBy = "mukboEntity", cascade = CascadeType.REMOVE)
    private List<EatenMukboEntity> eatenMukboEntities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mukgroup_id")
    private MukgroupEntity mukgroupEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public static MukboEntity create(String name, MukboType mukboType, Long mukgroupId){
        return MukboEntity.builder()
                .name(name)
                .type(mukboType)
                .mukgroupEntity(MukgroupEntity.createWithOnlyId(mukgroupId))
                .build();
    }
    public static MukboEntity createMukbo(String name, MukboType mukboType, Long mukgroupId, UserEntity user){
        return MukboEntity.builder()
            .name(name)
            .type(mukboType)
            .mukgroupEntity(MukgroupEntity.createWithOnlyId(mukgroupId))
            .userEntity(user)
            .build();
    }

    public static MukboEntity createMukbot(String name, Long groupId){
        return MukboEntity.builder()
            .name(name)
            .type(MukboType.MUKBOT)
            .mukgroupEntity(MukgroupEntity.createWithOnlyId(groupId))
            .build();

    }

    public void assiciatedWithMukBTIResult(List<MukBTIResultEntity> entities){
        this.mukBTIResultEntities = entities;
    }

    public MukboEntity modifyGroup(Long mukgroupId, Long userId){
        return MukboEntity.builder()
            .mukboId(this.mukboId)
                .name(this.name)
                .type(this.type)
                .mukgroupEntity(MukgroupEntity.createWithOnlyId(mukgroupId))
                .userEntity(UserEntity.createWithOnlyUserId(userId))
                .build();
    }


    public void modifyName(String name){
        this.name = name;
    }

    public void modifyMukBTIResult(List<MukBTIResultEntity> mukBTIResults){
        this.mukBTIResultEntities = mukBTIResults;
    }


}
