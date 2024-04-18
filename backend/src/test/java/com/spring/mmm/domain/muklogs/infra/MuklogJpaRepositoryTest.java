package com.spring.mmm.domain.muklogs.infra;

import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukgroups.infra.MukgroupJpaRepository;
import com.spring.mmm.domain.muklogs.domain.MuklogEntity;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MuklogJpaRepositoryTest {

    @Autowired
    MuklogJpaRepository muklogJpaRepository;

    @Autowired
    MukgroupJpaRepository mukgroupJpaRepository;

    @Test
    void pagingSortTest() throws Exception {
        // given
        MukgroupEntity group = MukgroupEntity.create("hello", false);
        mukgroupJpaRepository.save(group);
        for (int i = 0; i < 11; i++) {
            muklogJpaRepository.save(MuklogEntity.create(group, "안농"));
        }
        // when
        Page<MuklogEntity> sut = muklogJpaRepository.findAllMuklogByGroupId(group.getMukgroupId(), PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createdAt")));
        // then
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(sut.getSize()).isSameAs(10);
        sa.assertAll();
    }
}