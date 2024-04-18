package com.spring.mmm.domain.recommends.controller.request;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class XYRequest {

    private Double latitude;
    private Double longitude;
}
