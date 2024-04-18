package com.spring.mmm.domain.mukus.service;

import com.spring.mmm.domain.mukus.controller.response.MukusCalendarResponse;
import com.spring.mmm.domain.mukus.controller.response.MukusDayResponse;
import com.spring.mmm.domain.mukus.controller.response.MukusDetailResponse;
import com.spring.mmm.domain.mukus.controller.response.MukusRecentResponse;

import java.util.List;

public interface MukusService {
    void selectRecentMukus(Long recommendFoodId);
    MukusCalendarResponse getMukusMonth(Long groupId, Integer year, Integer month);

    MukusRecentResponse getRecentMukus(Long groupId);

    MukusDetailResponse getMukusDetail(Long groupId, Integer year, Integer month, Integer day);
}
