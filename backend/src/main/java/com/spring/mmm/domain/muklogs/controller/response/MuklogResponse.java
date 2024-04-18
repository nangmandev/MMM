package com.spring.mmm.domain.muklogs.controller.response;

import com.spring.mmm.domain.muklogs.domain.MuklogEntity;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MuklogResponse {
    private String content;
    private Long createdAt;

    public static MuklogResponse createByMuklogEntity(MuklogEntity muklogEntity){
        return MuklogResponse.builder()
                .content(muklogEntity.getContent())
                .createdAt(muklogEntity.getCreatedAt().toEpochMilli())
                .build();
    }
}
