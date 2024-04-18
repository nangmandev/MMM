package com.spring.mmm.domain.mukgroups.infra;

import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MukboJpaRepository extends JpaRepository<MukboEntity, Long> {
    @Query("select m from MukboEntity m where m.userEntity.id=:userId")
    Optional<MukboEntity> findByUserId(Long userId);

    @Query("select m from MukboEntity m where m.mukgroupEntity.mukgroupId=:groupId and m.type=:mukboType")
    List<MukboEntity> findAllMukboByGroupIdAndMukboType(Long groupId, MukboType mukboType);

    @Query("select m from MukboEntity m where m.mukgroupEntity.mukgroupId=:groupId")
    List<MukboEntity> findAllMukboByGroupId(Long groupId);

    @Query("select m from MukboEntity m where m.mukboId=:mukboId")
    Optional<MukboEntity> findByMukboId(Long mukboId);
}
