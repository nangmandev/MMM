package com.spring.mmm.domain.mukgroups.controller.request;

import com.spring.mmm.domain.mbtis.domain.MBTI;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukbotCreateRequest {
    private String name;
    private MBTI mbti;
}
