package com.spring.mmm.domain.mbtis.controller.response;

import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukBTIQuestionsResponse {
    List<MukBTIQuestion> mukBTIQuestions;
}
