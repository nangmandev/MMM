package com.spring.mmm.domain.mukjuks.controller;

import com.spring.mmm.domain.mukjuks.controller.response.MukjukResponse;
import com.spring.mmm.domain.mukjuks.service.MukjukService;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MukjukController {

    // JWT, GRoupID를 받아야 함
    // 회원이 그룹 아이디에 해당하는 그룹에 접근 권한이 있는지 체크해야 함.

    private final MukjukService mukjukService;

    @GetMapping("/groups/{groupId}/badges")
    public ResponseEntity<MukjukResponse> findAllMukjuks(@PathVariable Long groupId, @AuthenticationPrincipal UserDetailsImpl users){
        return ResponseEntity.ok(mukjukService.findAllMukjuks(groupId, users));
    }
}
