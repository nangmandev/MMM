package com.spring.mmm.domain.mukgroups.controller;

import com.spring.mmm.domain.mukgroups.controller.request.*;
import com.spring.mmm.domain.mukgroups.controller.response.MukbosResponse;
import com.spring.mmm.domain.mukgroups.controller.response.MukgroupResponse;
import com.spring.mmm.domain.mukgroups.service.MukboService;
import com.spring.mmm.domain.mukgroups.service.MukgroupService;
import com.spring.mmm.domain.muklogs.controller.response.MuklogsResponse;
import com.spring.mmm.domain.muklogs.service.MuklogService;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import com.spring.mmm.domain.users.infra.UserEntity;
import com.spring.mmm.domain.users.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/groups")
public class MukGroupController {

    private final MukgroupService mukgroupService;
    private final MukboService mukboService;
    private final MuklogService muklogService;

    @GetMapping
    public ResponseEntity<MukgroupResponse> findMukgroup(@AuthenticationPrincipal UserDetailsImpl user){
        return ResponseEntity.ok(MukgroupResponse.createByMukgroupEntity(mukgroupService.findMyMukgroup(user.getEmail()), user.getUser().getMukboEntity()));
    }

    @PostMapping
    public ResponseEntity<Void> createMukGroup(
            @AuthenticationPrincipal UserDetailsImpl user,
            @RequestPart(value = "data", required = true) MukgroupCreateRequest mukgroupCreateRequest,
            @RequestPart(value = "image", required = false) MultipartFile image
    ){
        mukgroupService.saveMukGroup(mukgroupCreateRequest.getName(), user.getEmail(), image);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{groupId}/name")
    public ResponseEntity<Void> modifyGroupName(
            @PathVariable Long groupId,
            @RequestBody MukgroupModifyRequest mukgroupModifyRequest,
            @AuthenticationPrincipal UserDetailsImpl users){
        mukgroupService.modifyGroupName(groupId, mukgroupModifyRequest.getName(), users.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{groupId}/image")
    public ResponseEntity<Void> modifyGroupImage(
            @PathVariable Long groupId,
            @RequestPart(value = "image") MultipartFile image,
            @AuthenticationPrincipal UserDetailsImpl users){
        mukgroupService.modifyGroupImage(groupId, image, users.getEmail());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{groupId}/users")
    public ResponseEntity<MukbosResponse> findAllMukbos(@PathVariable Long groupId){
        return ResponseEntity.ok(MukbosResponse.builder()
                .users(mukboService.findAllMukboResponsesByGroupId(groupId))
                .build());
    }

    @GetMapping("/{groupId}/mukbots")
    public ResponseEntity<MukbosResponse> findAllMukbots(@PathVariable Long groupId){
        return ResponseEntity.ok(MukbosResponse.builder()
                .users(mukboService.findAllMukbotResponsesByGroupId(groupId))
                .build());
    }

    @PostMapping("/{groupId}/mukbots")
    public ResponseEntity<MukbotCreateRequest> saveMukbot(
            @AuthenticationPrincipal UserDetailsImpl user,
            @RequestBody MukbotCreateRequest mukbotCreateRequest){
        mukboService.saveMukbot(user.getEmail(), mukbotCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{groupId}/log")
    public ResponseEntity<MuklogsResponse> findAllLogsByPaging(@PathVariable Long groupId, Pageable pageable,
                                                               @AuthenticationPrincipal UserDetailsImpl userEntity){
        return ResponseEntity.ok(muklogService.findAllMuklogByGroupId(groupId, pageable, userEntity.getUser()));
    }

    @PostMapping("/{groupId}/users")
    public ResponseEntity<Void> inviteUser(
            @AuthenticationPrincipal UserDetailsImpl user,
            @PathVariable Long groupId,
            @RequestBody MukboInviteRequest mukboInviteRequest
    ){
        mukboService.inviteMukbo(user.getEmail(), groupId, mukboInviteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{groupId}/users/{userId}/nickname")
    public ResponseEntity<Void> modifyMukboName(
            @PathVariable Long userId,
            @RequestBody MukboModifyRequest mukboModifyRequest){
        mukboService.modifyMokbo(userId, mukboModifyRequest.getName());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{groupId}/mukbots/{mukbotsId}")
    public ResponseEntity<Void> modifyMukbot(
            @AuthenticationPrincipal UserDetailsImpl user,
            @PathVariable Long mukbotsId,
            @RequestBody MukbotModifyRequest mukbotModifyRequest
    ){
        mukboService.modifyMukbot(user.getEmail(), mukbotsId, mukbotModifyRequest.getMbti(), mukbotModifyRequest.getName());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{groupId}/mukbos/{mukboId}")
    public ResponseEntity<Void> deleteMukbo(
            @PathVariable Long groupId,
            @PathVariable Long mukboId,
            @AuthenticationPrincipal UserDetailsImpl users){
        mukgroupService.kickMukbo(users.getEmail(), groupId, mukboId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{groupId}/exit")
    public ResponseEntity<Void> exitMukgroup(
            @PathVariable Long groupId,
            @AuthenticationPrincipal UserDetailsImpl user){
        mukgroupService.exitMukgroup(user.getEmail(), groupId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{groupId}/mbti")
    public ResponseEntity<MukgroupMBTIResponse> getGroupMBTI(
            @PathVariable Long groupId,
            @RequestBody MukgroupMBTICalcRequest mbtiCalcRequest
    ){
        return ResponseEntity.ok(MukgroupMBTIResponse.builder()
                .mbti(mukgroupService.calcGroupMukBTI(groupId, mbtiCalcRequest))
                .build());
    }

    @PutMapping("/{groupId}/badges")
    public ResponseEntity<Void> changeRepMukjuk(@PathVariable Long groupId,
        @RequestBody RepMukjukModifyRequest request,
        @AuthenticationPrincipal UserDetailsImpl users){
        mukgroupService.modifyGroupMukjuk(groupId, request.getBadgeId(), users.getEmail());
        return ResponseEntity.ok().build();
    }
}
