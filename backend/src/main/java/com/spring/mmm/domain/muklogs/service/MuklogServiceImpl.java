package com.spring.mmm.domain.muklogs.service;

import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukgroups.exception.MukGroupErrorCode;
import com.spring.mmm.domain.mukgroups.exception.MukGroupException;
import com.spring.mmm.domain.mukgroups.exception.MukboErrorCode;
import com.spring.mmm.domain.mukgroups.exception.MukboException;
import com.spring.mmm.domain.mukgroups.service.port.MukboRepository;
import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import com.spring.mmm.domain.muklogs.controller.response.MuklogResponse;
import com.spring.mmm.domain.muklogs.controller.response.MuklogsResponse;
import com.spring.mmm.domain.muklogs.domain.MuklogEntity;
import com.spring.mmm.domain.muklogs.exception.MukgroupNotFoundException;
import com.spring.mmm.domain.muklogs.service.port.MuklogRepository;
import com.spring.mmm.domain.users.infra.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MuklogServiceImpl implements MuklogService{
    private final MuklogRepository muklogRepository;
    private final MukgroupRepository mukgroupRepository;
    private final MukboRepository mukboRepository;


    @Override
    public MuklogsResponse findAllMuklogByGroupId(Long groupId, Pageable pageable, UserEntity userEntity) {

        MukgroupEntity group = mukgroupRepository.findByMukgroupId(groupId)
                .orElseThrow(() -> new MukGroupException(MukGroupErrorCode.NOT_FOUND));
        MukboEntity mukboEntity = mukboRepository.findByUserId(userEntity.getId())
                .orElseThrow(() -> new MukboException(MukboErrorCode.NOT_FOUND));
        if (!mukboEntity.getMukgroupEntity().equals(group)) {
            throw new MukGroupException(MukGroupErrorCode.FORBIDDEN);
        }

        // 회원이 group에 속하는지 체크
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<MuklogEntity> result = muklogRepository.findAllMuklogByGroupId(groupId, pageable);
        return MuklogsResponse.builder()
                .contents(result.getContent().stream()
                        .map(MuklogResponse::createByMuklogEntity)
                        .collect(Collectors.toList()))
                .hasNext(result.hasNext())
                .build();
    }

    @Override
    public void saveLog(Long mukgroupId, String content) {
        MukgroupEntity mukGroup = mukgroupRepository.findByMukgroupId(mukgroupId)
                .orElseThrow(() -> new MukGroupException(MukGroupErrorCode.NOT_FOUND));
        muklogRepository.save(MuklogEntity.create(mukGroup, content));
    }
}
