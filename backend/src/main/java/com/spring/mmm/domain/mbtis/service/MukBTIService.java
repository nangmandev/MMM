package com.spring.mmm.domain.mbtis.service;

import com.spring.mmm.domain.mbtis.controller.request.MukBTICalcRequest;
import com.spring.mmm.domain.mbtis.controller.response.MukBTIResponse;
import com.spring.mmm.domain.mbtis.controller.response.MukBTIResult;
import com.spring.mmm.domain.mbtis.domain.MukBTIQuestionEntity;
import com.spring.mmm.domain.users.infra.UserEntity;

import java.util.List;

public interface MukBTIService {
    List<MukBTIQuestionEntity> findAllMukBTIQuestion();

    MukBTIResult calcMBTI(MukBTICalcRequest mukBTICalcRequest);

    void save(String email, String key);

    MukBTIResponse getMukBTI(String email);
}
