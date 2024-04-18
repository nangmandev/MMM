package com.spring.mmm.domain.mbtis.controller.request;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukBTICalcInfo {
    private Integer quizId;
    private Integer answerId;
}
