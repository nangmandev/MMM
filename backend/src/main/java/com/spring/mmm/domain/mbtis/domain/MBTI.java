package com.spring.mmm.domain.mbtis.domain;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MBTI implements Serializable {
    private Integer ei;
    private Integer ns;
    private Integer tf;
    private Integer jp;
    private Integer mint;
    private Integer pine;
    private Integer die;

    public static MBTI create(List<MukBTIResultEntity> mukBTIResultEntities){
        MBTIBuilder mbtiBuilder = new MBTIBuilder();
        for(MukBTIResultEntity mukBTIResult : mukBTIResultEntities){
            switch (mukBTIResult.getMukBTIEntity().getType()){
                case MukBTIType.EI -> mbtiBuilder.ei(mukBTIResult.getScore());
                case MukBTIType.NS -> mbtiBuilder.ns(mukBTIResult.getScore());
                case MukBTIType.TF -> mbtiBuilder.tf(mukBTIResult.getScore());
                case MukBTIType.JP -> mbtiBuilder.jp(mukBTIResult.getScore());
                case MukBTIType.MINT -> mbtiBuilder.mint(mukBTIResult.getScore());
                case MukBTIType.PINE -> mbtiBuilder.pine(mukBTIResult.getScore());
                case MukBTIType.DIE -> mbtiBuilder.die(mukBTIResult.getScore());
            }
        }
        return mbtiBuilder.build();
    }

    public void modifyScore(Integer score, MukBTIType mukBTIType){
        switch (mukBTIType){
            case MukBTIType.EI -> this.ei = score;
            case MukBTIType.NS -> this.ns = score;
            case MukBTIType.TF -> this.tf = score;
            case MukBTIType.JP -> this.jp = score;
            case MukBTIType.MINT -> this.mint = score;
            case MukBTIType.PINE -> this.pine = score;
            case MukBTIType.DIE -> this.die = score;
        }
    }
}
