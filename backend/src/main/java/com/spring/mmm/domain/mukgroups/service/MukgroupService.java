package com.spring.mmm.domain.mukgroups.service;

import com.spring.mmm.domain.mbtis.domain.MBTI;
import com.spring.mmm.domain.mukgroups.controller.request.MukgroupMBTICalcRequest;
import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import com.spring.mmm.domain.users.infra.UserEntity;
import org.springframework.web.multipart.MultipartFile;

public interface MukgroupService {
    void saveSoloMukGroup(String email);
    void saveMukGroup(String name, String email, MultipartFile image);

    MukgroupEntity findMyMukgroup(String email);

    MukgroupEntity findMukgroupById(Long groupId);

    void modifyGroupName(Long groupId, String name, String email);

    void modifyGroupImage(Long groupId, MultipartFile multipartFile, String email);

    void kickMukbo(String email, Long groupId, Long mukboId);

    void exitMukgroup(String email, Long groupId);

    MBTI calcGroupMukBTI(Long groupId, MukgroupMBTICalcRequest mbtiCalcRequest);

	void modifyGroupMukjuk(Long groupId, Long badgeId, String email);
}
