package com.spring.mmm.domain.mbtis.controller.response;

import com.spring.mmm.domain.mbtis.domain.MBTI;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukBTIResult {
    private String key;
    private MBTI mbti;
}
