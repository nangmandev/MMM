package com.spring.mmm.domain.recommends.service.port;

import com.spring.mmm.domain.recommends.domain.EatenMukboEntity;

import java.time.LocalDate;
import java.util.Optional;

public interface EatenMukboRepository {
    void save(EatenMukboEntity eatenMukboEntity);

    void deleteAllByDateAndGroupId(LocalDate date, Long groupId);

    Boolean existsByDateAndGroupId(LocalDate date, Long groupId);
}
