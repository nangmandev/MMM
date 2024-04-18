package com.spring.mmm.domain.recommends.infra;

import com.spring.mmm.domain.recommends.domain.EatenMukboEntity;
import com.spring.mmm.domain.recommends.service.port.EatenMukboRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EatenMukboRepositoryImpl implements EatenMukboRepository {

    private final EatenMukboJpaRepository eatenMukboJpaRepository;

    @Override
    public void save(EatenMukboEntity eatenMukboEntity) {
        eatenMukboJpaRepository.save(eatenMukboEntity);
    }

    @Override
    public void deleteAllByDateAndGroupId(LocalDate date, Long groupId) {
        eatenMukboJpaRepository.deleteAllByDateAndGroupId(date, groupId);
    }

    @Override
    public Boolean existsByDateAndGroupId(LocalDate date, Long groupId) {
        return eatenMukboJpaRepository.existsByDateAndGroupId(date, groupId);
    }


}
