package com.spring.mmm.domain.recommends.controller.request;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class LunchRecommendRequest {
    private Integer EI;
    private Integer NS;
    private Integer TF;
    private Integer JP;
}
