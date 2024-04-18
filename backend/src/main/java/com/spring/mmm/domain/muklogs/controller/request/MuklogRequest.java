package com.spring.mmm.domain.muklogs.controller.request;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MuklogRequest {
    private Integer page;
    private Integer size;
}
