package com.spring.mmm.domain.mukjuks.service.port;

import com.spring.mmm.domain.mukjuks.controller.response.Badge;
import com.spring.mmm.domain.mukjuks.domain.MukjukEntity;

import java.util.List;

public interface MukjukRepository {
    List<Badge> findAllBadges(Long groupId);

    List<MukjukEntity> findUnclearedMukjuk(Long groupId);

    Long getMukgetIdIfUncleared(Long groupId, String mukjukTitle);
}
