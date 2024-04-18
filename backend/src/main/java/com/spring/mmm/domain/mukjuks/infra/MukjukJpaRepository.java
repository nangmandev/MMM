package com.spring.mmm.domain.mukjuks.infra;

import com.spring.mmm.domain.mukjuks.controller.response.Badge;
import com.spring.mmm.domain.mukjuks.domain.MukjukEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MukjukJpaRepository extends JpaRepository<MukjukEntity, Long> {

    @Query("select new com.spring.mmm.domain.mukjuks.controller.response.Badge(mj.mukjukId, mj.name, mj.context, CASE WHEN mgmj is null THEN false" +
            " ELSE true " +
            " END , mj.imageSrc ) " +
            "from MukgroupMukjukEntity mgmj right join mgmj.mukjukEntity mj " +
            "on mgmj.mukgroupEntity.mukgroupId =:groupId")
    List<Badge> findAllMukjuks(Long groupId);




    @Query("select case when mgmj is null THEN mj.mukjukId else -1 end " +
            "from MukgroupMukjukEntity mgmj right join mgmj.mukjukEntity mj " +
            "on mgmj.mukgroupEntity.mukgroupId =:groupId" +
            " where mj.name=:mukjukTitle")
    Long getMukgetIdIfUncleared(Long groupId, String mukjukTitle);

    @Query("select mj from MukgroupMukjukEntity mgmj right join mgmj.mukjukEntity mj " +
            " on mgmj.mukgroupEntity.mukgroupId =:groupId where mgmj is null")
    List<MukjukEntity> findUnclearedMukjuk(Long groupId);
}
