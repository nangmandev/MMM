package com.spring.mmm.domain.mukgroups.infra;

import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import com.spring.mmm.domain.mukgroups.exception.MukboErrorCode;
import com.spring.mmm.domain.mukgroups.exception.MukboException;
import com.spring.mmm.domain.mukgroups.service.port.MukboRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MukboRepositoryImpl implements MukboRepository {
    private final MukboJpaRepository mukboJpaRepository;
    @Override
    public void save(MukboEntity mukboEntity) {
        mukboJpaRepository.save(mukboEntity);
    }

    @Override
    public void saveAndFlush(MukboEntity mukboEntity) {
        mukboJpaRepository.saveAndFlush(mukboEntity);
    }

    @Override
    public Optional<MukboEntity> findByUserId(Long userId) {
        return mukboJpaRepository.findByUserId(userId);
    }

    @Override
    public List<MukboEntity> findAllMukboByGroupId(Long groupId) {
        return mukboJpaRepository.findAllMukboByGroupId(groupId);
    }

    @Override
    public Optional<MukboEntity> findByMukboId(Long mukboId) {
        return mukboJpaRepository.findByMukboId(mukboId);
    }

    @Override
    public void delete(MukboEntity mukboEntity) {
        mukboJpaRepository.delete(mukboEntity);
    }


}
