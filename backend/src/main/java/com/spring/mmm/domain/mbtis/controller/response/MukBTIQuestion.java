package com.spring.mmm.domain.mbtis.controller.response;

import com.spring.mmm.domain.mbtis.domain.MukBTIQuestionEntity;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukBTIQuestion {
    private Integer quizId;
    private String content;
    private String imageSrc;
    private List<MukBTIAnswerResponse> answers;

    public static MukBTIQuestion createByMukBTIQuestionEntity(MukBTIQuestionEntity mukBTIQuestionEntity){
        return MukBTIQuestion.builder()
                .quizId(mukBTIQuestionEntity.getQuestionId())
                .content(mukBTIQuestionEntity.getContent())
                .imageSrc(mukBTIQuestionEntity.getImageSrc())
                .answers(mukBTIQuestionEntity.getMukBTIAnswerEntities().stream()
                        .map(MukBTIAnswerResponse::createByMukBTIAnswerEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
