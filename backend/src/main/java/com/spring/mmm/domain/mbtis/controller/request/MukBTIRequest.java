package com.spring.mmm.domain.mbtis.controller.request;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukBTIRequest {
    private String key;
}
