package com.spring.mmm.domain.mbtis.domain;

import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.users.infra.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "result")
@Entity
public class MukBTIResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Integer resultId;

    @Column(name = "score")
    private Integer score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mbti_id")
    private MukBTIEntity mukBTIEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mukbo_id")
    private MukboEntity mukboEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public void modifyScore(Integer score){
        this.score = score;
    }

    public static MukBTIResultEntity createByType(Integer score, MukBTIEntity mukBTIEntity, MukboEntity mukboEntity){
        return MukBTIResultEntity.builder()
                .score(score)
                .mukBTIEntity(mukBTIEntity)
                .mukboEntity(mukboEntity)
                .build();
    }

    public static MukBTIResultEntity createByType(Integer score, MukBTIEntity mukBTIEntity, MukboEntity mukboEntity, UserEntity user){
        return MukBTIResultEntity.builder()
                .score(score)
                .mukBTIEntity(mukBTIEntity)
                .mukboEntity(mukboEntity)
                .userEntity(user)
                .build();
    }

    public static MukBTIResultEntity createWithoutScore(MukBTIEntity mukBTIEntity, MukboEntity mukboEntity, UserEntity userEntity){
        return MukBTIResultEntity.builder()
                .mukBTIEntity(mukBTIEntity)
                .mukboEntity(mukboEntity)
                .userEntity(userEntity)
                .build();
    }

    public static List<MukBTIResultEntity> createByMBTI(MBTI mbti, List<MukBTIEntity> mukBTIEntities, MukboEntity mukbo){
        List<MukBTIResultEntity> mukBTIResultEntities = new ArrayList<>();
        for(MukBTIEntity mukBTI : mukBTIEntities){
            switch (mukBTI.getType()){
                case EI -> mukBTIResultEntities.add(MukBTIResultEntity.createByType(mbti.getEi(), mukBTI, mukbo));
                case NS -> mukBTIResultEntities.add(MukBTIResultEntity.createByType(mbti.getNs(), mukBTI, mukbo));
                case TF -> mukBTIResultEntities.add(MukBTIResultEntity.createByType(mbti.getTf(), mukBTI, mukbo));
                case JP -> mukBTIResultEntities.add(MukBTIResultEntity.createByType(mbti.getJp(), mukBTI, mukbo));
                case MINT -> mukBTIResultEntities.add(MukBTIResultEntity.createByType(mbti.getMint(), mukBTI, mukbo));
                case PINE -> mukBTIResultEntities.add(MukBTIResultEntity.createByType(mbti.getPine(), mukBTI, mukbo));
                case DIE -> mukBTIResultEntities.add(MukBTIResultEntity.createByType(mbti.getDie(), mukBTI, mukbo));
            }
        }
        return mukBTIResultEntities;
    }
}
