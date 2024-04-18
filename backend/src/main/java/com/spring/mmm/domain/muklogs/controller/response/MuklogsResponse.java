package com.spring.mmm.domain.muklogs.controller.response;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MuklogsResponse {
    private List<MuklogResponse> contents;
    private Boolean hasNext;
}
