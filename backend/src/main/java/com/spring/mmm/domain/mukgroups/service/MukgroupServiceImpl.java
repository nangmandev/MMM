package com.spring.mmm.domain.mukgroups.service;

import com.spring.mmm.common.event.Events;
import com.spring.mmm.common.service.S3Service;
import com.spring.mmm.domain.mbtis.domain.MBTI;
import com.spring.mmm.domain.mbtis.domain.MukBTIResultEntity;
import com.spring.mmm.domain.mbtis.domain.MukBTIType;
import com.spring.mmm.domain.mbtis.service.port.MukBTIResultRepository;
import com.spring.mmm.domain.mukgroups.controller.request.MukgroupMBTICalcRequest;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import com.spring.mmm.domain.mukgroups.event.*;
import com.spring.mmm.domain.mukgroups.exception.MukGroupErrorCode;
import com.spring.mmm.domain.mukgroups.exception.MukGroupException;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukgroups.exception.MukboErrorCode;
import com.spring.mmm.domain.mukgroups.exception.MukboException;
import com.spring.mmm.domain.mukgroups.service.port.MukboRepository;
import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import com.spring.mmm.domain.users.exception.UserErrorCode;
import com.spring.mmm.domain.users.exception.UserException;
import com.spring.mmm.domain.users.infra.UserEntity;
import com.spring.mmm.domain.users.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MukgroupServiceImpl implements MukgroupService{
    private final MukgroupRepository mukgroupRepository;
    private final MukboRepository mukboRepository;
    private final S3Service s3Service;
    private final MukBTIResultRepository mukBTIResultRepository;
    private final UserRepository userRepository;
    @Override
    @Transactional
    public void saveSoloMukGroup(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        MukgroupEntity mukgroupEntity = MukgroupEntity.create(user.getNickname(), Boolean.TRUE);
        mukgroupRepository.save(mukgroupEntity);
        mukboRepository.save(mukboRepository.findByUserId(user.getId())
                        .orElseThrow(() -> new MukboException(MukboErrorCode.NOT_FOUND))
                .modifyGroup(mukgroupEntity.getMukgroupId(), user.getId()));
    }

    @Override
    @Transactional
    public void saveMukGroup(String name, String email, MultipartFile image) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        MukboEntity mukboEntity = user.getMukboEntity();
        MukgroupEntity originMukgroup = mukboEntity.getMukgroupEntity();
        if(originMukgroup.getIsSolo()){
            MukgroupEntity mukgroupEntity = MukgroupEntity
                    .create(name, Boolean.FALSE)
                    .modifyMukgroupImage(s3Service.uploadFile(image, true));
            mukgroupRepository.save(mukgroupEntity);
            mukboRepository.saveAndFlush(mukboEntity.modifyGroup(mukgroupEntity.getMukgroupId(), user.getId()));
            mukgroupRepository.delete(originMukgroup);
        } else {
            throw new MukGroupException(MukGroupErrorCode.DUPLICATE_ERROR);
        }
    }

    @Override
    public MukgroupEntity findMyMukgroup(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND))
                .getMukboEntity()
                .getMukgroupEntity();
    }

    @Override
    public MukgroupEntity findMukgroupById(Long groupId) {
        return getMukgroupEntity(groupId);
    }

    private MukgroupEntity getMukgroupEntity(Long groupId) {
        return mukgroupRepository.findByMukgroupId(groupId)
                .orElseThrow(() -> new MukGroupException(MukGroupErrorCode.NOT_FOUND));
    }

    @Override
    @Transactional
    public void modifyGroupName(Long groupId, String name, String email) {
        MukboEntity mukboEntity = userRepository.findByEmail(email)
                        .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND))
                                .getMukboEntity();
        mukgroupRepository.save(getMukgroupEntity(groupId).modifyMukgroupName(name));
        Events.raise(new MukgroupNameChangedEvent(mukboEntity.getName(),  name, groupId));
    }

    @Override
    @Transactional
    public void modifyGroupImage(Long groupId, MultipartFile multipartFile, String email) {
        MukboEntity mukboEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND))
                .getMukboEntity();
        String imageSrc = s3Service.uploadFile(multipartFile, true);
        mukgroupRepository.save(getMukgroupEntity(groupId).modifyMukgroupImage(imageSrc));
        Events.raise(new MukgroupImageChangedEvent(mukboEntity.getName(), groupId));
    }

    @Override
    @Transactional
    public void kickMukbo(String email, Long groupId, Long mukboId) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        MukboEntity sourceUser = user.getMukboEntity();

        if(!user.getMukboEntity().getMukgroupEntity().getMukgroupId().equals(groupId)){
            throw new MukGroupException(MukGroupErrorCode.FORBIDDEN);
        }

        MukboEntity mukboEntity = mukboRepository.findByMukboId(mukboId)
                .orElseThrow(() -> new MukboException(MukboErrorCode.NOT_FOUND));

        if(!user.getMukboEntity().getMukgroupEntity().getMukgroupId()
                .equals(mukboEntity.getMukgroupEntity().getMukgroupId())){
            throw new MukGroupException(MukGroupErrorCode.ANOTHER_MUKGROUP);
        }

        if(mukboEntity.getType() == MukboType.HUMAN) {
            saveSoloMukGroup(mukboEntity.getUserEntity().getEmail());
            Events.raise(new MukboKickedEvent(sourceUser.getName(), mukboEntity.getName(), sourceUser.getMukgroupEntity().getMukgroupId()));
        }
        else {
            mukboRepository.delete(mukboEntity);
            Events.raise(new MukbotDeletedEvent(sourceUser.getName(), mukboEntity.getName(), sourceUser.getMukgroupEntity().getMukgroupId()));
        }
    }

    @Override
    @Transactional
    public void exitMukgroup(String email, Long groupId) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        MukboEntity mukbo = user.getMukboEntity();

        MukgroupEntity mukgroup = mukbo.getMukgroupEntity();
        if(mukgroup.getIsSolo()){
            throw new MukGroupException(MukGroupErrorCode.SOLO_CANT_EXIT);
        }

        saveSoloMukGroup(user.getEmail());

        Events.raise(new MukboExitedEvent(mukbo.getName(), groupId));
    }


    @Override
    public MBTI calcGroupMukBTI(Long groupId, MukgroupMBTICalcRequest mbtiCalcRequest) {
        MBTI mbti = MBTI.builder().build();
        for(MukBTIType mukBTIType : MukBTIType.values()){
            int calcResult = calcMBTI(mukBTIResultRepository.findAllMukBTIResultByMukboIdAndMukBTIType(mbtiCalcRequest.getMukbos(), mukBTIType));
            mbti.modifyScore(calcResult, mukBTIType);
        }
        Events.raise(new GroupMukBTICalculatedEvent(groupId, mbti));
        return mbti;
    }

    @Transactional
    @Override
    public void modifyGroupMukjuk(Long groupId, Long badgeId, String email) {
        MukboEntity mukboEntity = userRepository.findByEmail(email)
            .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND))
            .getMukboEntity();
        MukgroupEntity mukgroup = getMukgroupEntity(groupId);
        if(mukgroup.getIsSolo()){
            throw new MukGroupException(MukGroupErrorCode.SOLO_CANT_EXIT);
        }
        if(!mukboEntity.getMukgroupEntity().getMukgroupId().equals(groupId)){
            throw new MukGroupException(MukGroupErrorCode.FORBIDDEN);
        }
        // 권한 체크
        mukgroup.modifyRepMukjuk(badgeId);
    }

    private Integer calcMBTI(List<MukBTIResultEntity> mukBTIResults){
        int sum = 0;
        for(MukBTIResultEntity mukBTIResult : mukBTIResults){
            sum += mukBTIResult.getScore();
        }
        return sum / mukBTIResults.size();
    }
}
