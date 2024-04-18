package com.spring.mmm.domain.mukjuks.infra;

import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukjuks.controller.response.Badge;
import com.spring.mmm.domain.mukjuks.domain.MukgroupMukjukEntity;
import com.spring.mmm.domain.mukjuks.domain.MukjukEntity;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MukjukJpaRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    MukjukJpaRepository mukjukJpaRepository;


    List<MukjukEntity> mukjuks;
    @BeforeEach
    void beforeEach(){
        mukjuks = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            MukjukEntity mukjuk = MukjukEntity.builder()
                    .name("먹적" + i)
                    .context("비밀~")
                    .build();
            mukjuks.add(mukjuk);
            em.persist(mukjuk);
        }
        em.flush();
    }

    @Test
    void 먹적조회_테스트() throws Exception {
        // given
        long groupId = 1L;
        // when
//        List<Badge> sut = mukjukJpaRepository.findAllMukjuks(groupId);
        List<Badge> sut = em.createQuery("select new com.spring.mmm.domain.mukjuks.controller.response.Badge(mj.mukjukId, mj.name, mj.context, CASE WHEN mgmj is null THEN false" +
                        " ELSE true" +
                        " END , mj.imageSrc ) " +
                        "from MukgroupMukjukEntity mgmj right join mgmj.mukjukEntity mj on mgmj.mukgroupEntity.id =:groupId" , Badge.class)
                .setParameter("groupId", groupId)
                .getResultList();
        // then
        Assertions.assertThat(sut.size()).isSameAs(10);
        System.out.println(sut);
    }

    @Test
    void 클리어하지_못한_먹적_테스트() throws Exception{
        List<MukjukEntity> sut = mukjukJpaRepository.findUnclearedMukjuk(1L);
        Assertions.assertThat(sut.size()).isSameAs(10);
    }
    @Test
    void 클리어하지_못한_먹적_테스트_먹적을_다_꺤_경우() throws Exception{
        MukgroupEntity group = MukgroupEntity.builder()
                .build();
        em.persist(group);
        for (MukjukEntity mukjuk : mukjuks) {
            em.persist(MukgroupMukjukEntity.create(group, mukjuk));;
        }
        em.flush();
        List<MukjukEntity> sut = mukjukJpaRepository.findUnclearedMukjuk(group.getMukgroupId());
        Assertions.assertThat(sut).isEmpty();

    }
}