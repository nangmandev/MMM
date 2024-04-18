package com.spring.mmm.domain.mukus.controller.response;

import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukusCalendarResponse {

    private List<MukusDayResponse> res;

    public static MukusCalendarResponse create(List res) {
        return MukusCalendarResponse.builder()
                .res(res)
                .build();
    }
}
