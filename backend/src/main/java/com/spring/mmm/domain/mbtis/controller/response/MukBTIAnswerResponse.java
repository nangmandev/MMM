package com.spring.mmm.domain.mbtis.controller.response;

import com.spring.mmm.domain.mbtis.domain.MukBTIAnswerEntity;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukBTIAnswerResponse {
    private Integer answerId;
    private String answerContent;
    private String answerImageSrc;

    public static MukBTIAnswerResponse createByMukBTIAnswerEntity(MukBTIAnswerEntity mukBTIAnswerEntity){
        return MukBTIAnswerResponse.builder()
                .answerId(mukBTIAnswerEntity.getAnswerId())
                .answerContent(mukBTIAnswerEntity.getContent())
                .answerImageSrc(mukBTIAnswerEntity.getImageSrc())
                .build();
    }
}
