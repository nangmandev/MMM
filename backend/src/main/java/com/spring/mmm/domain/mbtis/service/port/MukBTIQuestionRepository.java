package com.spring.mmm.domain.mbtis.service.port;

import com.spring.mmm.domain.mbtis.domain.MukBTIQuestionEntity;

import java.util.List;

public interface MukBTIQuestionRepository {
    List<MukBTIQuestionEntity> findAllMukBTIQuestion();
}
