package com.spring.mmm.domain.mukgroups.controller.response;

import com.spring.mmm.domain.mukjuks.controller.response.Badge;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukgroupMukjukResponse {
    private Long titleMukjukId;
    private List<Badge> badges;
}
