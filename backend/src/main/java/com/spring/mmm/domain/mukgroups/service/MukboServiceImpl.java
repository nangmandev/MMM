package com.spring.mmm.domain.mukgroups.service;


import com.spring.mmm.common.event.Events;
import com.spring.mmm.domain.mbtis.domain.MBTI;
import com.spring.mmm.domain.mbtis.domain.MukBTIEntity;
import com.spring.mmm.domain.mbtis.domain.MukBTIResultEntity;
import com.spring.mmm.domain.mbtis.domain.MukBTIType;
import com.spring.mmm.domain.mbtis.service.port.MukBTIRepository;
import com.spring.mmm.domain.mbtis.service.port.MukBTIResultRepository;
import com.spring.mmm.domain.mukgroups.controller.request.MukboInviteRequest;
import com.spring.mmm.domain.mukgroups.controller.request.MukbotCreateRequest;
import com.spring.mmm.domain.mukgroups.controller.response.MukboResponse;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import com.spring.mmm.domain.mukgroups.event.MukboInvitedEvent;
import com.spring.mmm.domain.mukgroups.event.MukboNicknameChangedEvent;
import com.spring.mmm.domain.mukgroups.event.MukbotModifiedEvent;
import com.spring.mmm.domain.mukgroups.exception.MukGroupErrorCode;
import com.spring.mmm.domain.mukgroups.exception.MukGroupException;
import com.spring.mmm.domain.mukgroups.exception.MukboErrorCode;
import com.spring.mmm.domain.mukgroups.exception.MukboException;
import com.spring.mmm.domain.mukgroups.service.port.MukboRepository;
import com.spring.mmm.domain.users.exception.UserErrorCode;
import com.spring.mmm.domain.users.exception.UserException;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import com.spring.mmm.domain.users.infra.UserEntity;
import com.spring.mmm.domain.users.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MukboServiceImpl implements MukboService{
    private final MukboRepository mukboRepository;
    private final UserRepository userRepository;
    private final MukBTIRepository mukBTIRepository;
    private final MukBTIResultRepository mukBTIResultRepository;

    @Override
    public List<MukboResponse> findAllMukboResponsesByGroupId(Long groupId) {
        return mukboRepository.findAllMukboByGroupId(groupId)
                .stream()
            .filter(item -> item.getType().equals(MukboType.HUMAN))
                .map(MukboResponse::create)
                .collect(Collectors.toList());
    }

    @Override
    public List<MukboResponse> findAllMukbotResponsesByGroupId(Long groupId) {
        return mukboRepository.findAllMukboByGroupId(groupId)
                .stream()
                .filter(item -> item.getType().equals(MukboType.MUKBOT))
                .map(MukboResponse::create)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void inviteMukbo(String email, Long groupId, MukboInviteRequest mukboInviteRequest) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        MukboEntity mukboEntity = user.getMukboEntity();

        if(!mukboEntity.getMukgroupEntity().getMukgroupId().equals(groupId)){
            throw new MukGroupException(MukGroupErrorCode.FORBIDDEN);
        }

        if(mukboEntity.getMukgroupEntity().getIsSolo()){
            throw new MukGroupException(MukGroupErrorCode.SOLOGROUP_CANT_INVITE);
        }

        if(mukboInviteRequest.getMukbotId() != null
        && mukboInviteRequest.getMukbotId() != 0L) {
            mukboRepository.delete(mukboRepository.findByMukboId(mukboInviteRequest.getMukbotId())
                    .orElseThrow(() -> new MukboException(MukboErrorCode.NOT_FOUND)));
        }

        UserEntity friend = userRepository.findByEmail(mukboInviteRequest.getEmail())
            .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        MukboEntity invitedMukbo = friend.getMukboEntity();

        if(invitedMukbo.getMukgroupEntity().getMukgroupId().equals(groupId)){
            throw new MukGroupException(MukGroupErrorCode.ALREADY_EXISTS);
        }

        if(!invitedMukbo.getMukgroupEntity().getIsSolo()){
            throw new MukGroupException(MukGroupErrorCode.DUPLICATE_ERROR);
        }

        invitedMukbo.modifyName(mukboInviteRequest.getNickname());
        mukboRepository.save(invitedMukbo.modifyGroup(groupId, friend.getId()));

        Events.raise(new MukboInvitedEvent(user.getMukboEntity().getName(),mukboInviteRequest.getNickname(), groupId));
    }


    @Override
    @Transactional
    public void modifyMukbot(String email, Long mukboId, MBTI mbti, String name) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        MukboEntity mukbotEntity = mukboRepository.findByMukboId(mukboId)
                .orElseThrow(() -> new MukboException(MukboErrorCode.NOT_FOUND));

        if(!user.getMukboEntity().getMukgroupEntity().getMukgroupId().equals(mukbotEntity.getMukgroupEntity().getMukgroupId())){
            throw new MukboException(MukboErrorCode.ANOTHER_GROUP);
        }

        mukbotEntity.modifyName(name);

        List<MukBTIResultEntity> mukBTIResults = mukBTIResultRepository.findAllMukBTIResultByMukboId(mukboId);

        // 먹봇이면 유저 넣을 필요 X
        if(mukBTIResults.isEmpty()){
            createMukBTIResults(mukBTIResults, mukbotEntity, mbti);
        } else {
            modifyMukBTIResults(mukBTIResults, mbti);
        }

        mukbotEntity.modifyMukBTIResult(mukBTIResults);
        mukboRepository.save(mukbotEntity);

        Events.raise(new MukbotModifiedEvent(user.getMukboEntity().getName(), name, user.getMukboEntity().getMukgroupEntity().getMukgroupId()));
    }

    @Override
    @Transactional
    public void modifyMokbo(Long userId, String name) {
        MukboEntity mukboEntity = mukboRepository.findByUserId(userId)
                .orElseThrow(() -> new MukboException(MukboErrorCode.NOT_FOUND));
        mukboEntity.modifyName(name);
        mukboRepository.save(mukboEntity);
        Events.raise(new MukboNicknameChangedEvent(name, mukboEntity.getMukgroupEntity().getMukgroupId()));
    }

    @Override
    @Transactional
    public void saveMukbot(String email, MukbotCreateRequest mukbotCreateRequest) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        if(user.getMukboEntity().getMukgroupEntity().getIsSolo()){
            throw new MukGroupException(MukGroupErrorCode.SOLOGROUP_CANT_INVITE);
        }


        MukboEntity mukbot = MukboEntity.createMukbot(mukbotCreateRequest.getName(),
            user.getMukboEntity().getMukgroupEntity().getMukgroupId());

        mukbot.assiciatedWithMukBTIResult(MukBTIResultEntity.createByMBTI(mukbotCreateRequest.getMbti(), mukBTIRepository.findAllMukBTI(),mukbot));
        mukboRepository.save(mukbot);

    }

    @Override
    @Transactional
    public void deleteMukbo(Long mukboId) {
        mukboRepository.delete(mukboRepository.findByMukboId(mukboId)
                .orElseThrow(() -> new MukboException(MukboErrorCode.NOT_FOUND)));
    }

    private void createMukBTIResults(List<MukBTIResultEntity> results, MukboEntity mukbo, MBTI mbti){
        // 원래 mbti가 없는경우
        List<MukBTIEntity> mukBTIs = mukBTIRepository.findAllMukBTI();
        for(MukBTIEntity mukBTI : mukBTIs){
            switch (mukBTI.getType()){
                case MukBTIType.EI -> results.add(MukBTIResultEntity.createByType(mbti.getEi(), mukBTI, mukbo));
                case MukBTIType.NS -> results.add(MukBTIResultEntity.createByType(mbti.getNs(), mukBTI, mukbo));
                case MukBTIType.TF -> results.add(MukBTIResultEntity.createByType(mbti.getTf(), mukBTI, mukbo));
                case MukBTIType.JP -> results.add(MukBTIResultEntity.createByType(mbti.getJp(), mukBTI, mukbo));
                case MukBTIType.PINE -> results.add(MukBTIResultEntity.createByType(mbti.getPine(), mukBTI, mukbo));
                case MukBTIType.MINT -> results.add(MukBTIResultEntity.createByType(mbti.getMint(), mukBTI, mukbo));
                case MukBTIType.DIE -> results.add(MukBTIResultEntity.createByType(mbti.getDie(), mukBTI, mukbo));
            }
        }
    }

    private void modifyMukBTIResults(List<MukBTIResultEntity> results, MBTI mbti){
        // 원래 mbti가 있는경우
        for(MukBTIResultEntity mukBTIResult : results){
            switch (mukBTIResult.getMukBTIEntity().getType()){
                case MukBTIType.EI -> mukBTIResult.modifyScore(mbti.getEi());
                case MukBTIType.NS -> mukBTIResult.modifyScore(mbti.getNs());
                case MukBTIType.TF -> mukBTIResult.modifyScore(mbti.getTf());
                case MukBTIType.JP -> mukBTIResult.modifyScore(mbti.getJp());
                case MukBTIType.MINT -> mukBTIResult.modifyScore(mbti.getMint());
                case MukBTIType.PINE -> mukBTIResult.modifyScore(mbti.getPine());
                case MukBTIType.DIE -> mukBTIResult.modifyScore(mbti.getDie());
            }
        }
    }
}
