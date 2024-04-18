package com.spring.mmm.domain.recommends.controller.response;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class WeatherDTO {
    private float PTY; // 강수형태
    private float RN1; // 1시간 강수량
    private float REH; // 습도
    private float T1H; // 기온
    private float UUU; // 동서바람성분
    private float VVV; // 남북바람성분
    private float VEC; // 풍향
    private float WSD; // 풍속

    public static WeatherDTO create(Float PTY, Float RN1, Float REH, Float T1H, Float UUU, Float VVV, Float VEC, Float WSD) {

        return WeatherDTO.builder()
                .PTY(PTY)
                .RN1(RN1)
                .REH(REH)
                .T1H(T1H)
                .UUU(UUU)
                .VVV(VVV)
                .VEC(VEC)
                .WSD(WSD)
                .build();
    }
}
