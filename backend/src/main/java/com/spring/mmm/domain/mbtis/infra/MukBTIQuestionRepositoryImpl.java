package com.spring.mmm.domain.mbtis.infra;

import com.spring.mmm.domain.mbtis.domain.MukBTIQuestionEntity;
import com.spring.mmm.domain.mbtis.service.port.MukBTIQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MukBTIQuestionRepositoryImpl implements MukBTIQuestionRepository {
    private final MukBTIQuestionJpaRepository mukBTIQuestionJpaRepository;
    @Override
    public List<MukBTIQuestionEntity> findAllMukBTIQuestion() {
        return mukBTIQuestionJpaRepository.findAllMukBTIQuestion();
    }
}
