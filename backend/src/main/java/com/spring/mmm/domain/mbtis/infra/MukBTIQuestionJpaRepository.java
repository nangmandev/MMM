package com.spring.mmm.domain.mbtis.infra;

import com.spring.mmm.domain.mbtis.domain.MukBTIQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MukBTIQuestionJpaRepository extends JpaRepository<MukBTIQuestionEntity, Integer> {

    @Query("select mq from MukBTIQuestionEntity mq")
    List<MukBTIQuestionEntity> findAllMukBTIQuestion();
}
