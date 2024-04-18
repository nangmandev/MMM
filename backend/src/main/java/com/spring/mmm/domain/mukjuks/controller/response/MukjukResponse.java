package com.spring.mmm.domain.mukjuks.controller.response;

import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class MukjukResponse {

    private Long titleMukjukId;

    private List<Badge> badges;

    public static MukjukResponse create(MukgroupEntity group, List<Badge> badges){
        Long titleMukjukId = group.getMukjukEntity() == null ? null : group.getMukjukEntity().getMukjukId();
        return MukjukResponse.builder()
                .titleMukjukId(titleMukjukId)
                .badges(badges)
                .build();

    }


}
