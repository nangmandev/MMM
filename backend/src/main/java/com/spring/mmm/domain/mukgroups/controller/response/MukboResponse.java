package com.spring.mmm.domain.mukgroups.controller.response;

import com.spring.mmm.domain.mbtis.domain.MBTI;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukboResponse {
    private Long mukboId;
    private MukboType type;
    private String name;
    private String mukBTI;
    private MBTI mbti;

    public static MukboResponse create(MukboEntity mukbo){
        return MukboResponse.builder()
                .mukboId(mukbo.getMukboId())
                .name(mukbo.getName())
                .type(mukbo.getType())
                .mukBTI(calcMukBTI(MBTI.create(mukbo.getMukBTIResultEntities())))
                .mbti(MBTI.create(mukbo.getMukBTIResultEntities()))
                .build();
    }

    private static String calcMukBTI(MBTI mbti){
        char[] res = new char[4];

        res[0] = mbti.getEi() > 15 ? 'I' : 'E';
        res[1] = mbti.getNs() > 15 ? 'S' : 'N';
        res[2] = mbti.getTf() > 15 ? 'F' : 'T';
        res[3] = mbti.getJp() > 15 ? 'P' : 'J';

        return String.copyValueOf(res);
    }
}
