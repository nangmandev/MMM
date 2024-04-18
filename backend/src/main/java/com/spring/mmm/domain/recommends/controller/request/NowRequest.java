package com.spring.mmm.domain.recommends.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class NowRequest {
    @NotBlank(message = "아무도 식사 안하시나요?")
    private List<Long> nowMukbos;
}
