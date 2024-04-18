package com.spring.mmm.domain.users.controller;

import com.spring.mmm.common.config.RedisDao;
import com.spring.mmm.common.config.jwt.JwtProvider;
import com.spring.mmm.domain.mbtis.controller.request.MukBTIRequest;
import com.spring.mmm.domain.mbtis.controller.response.MukBTIResponse;
import com.spring.mmm.domain.mbtis.service.MukBTIService;
import com.spring.mmm.domain.users.controller.request.*;
import com.spring.mmm.domain.users.controller.response.LoginResponse;
import com.spring.mmm.domain.users.controller.response.TokenResponse;
import com.spring.mmm.domain.users.controller.response.UserInfoResponse;
import com.spring.mmm.domain.users.exception.UserErrorCode;
import com.spring.mmm.domain.users.exception.UserException;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import com.spring.mmm.domain.users.infra.UserEntity;
import com.spring.mmm.domain.users.service.UserEmailSendService;
import com.spring.mmm.domain.users.service.UserService;
import com.spring.mmm.domain.users.service.port.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final UserEmailSendService userEmailSendService;
    private final MukBTIService mukBTIService;
    private final RedisDao redisDao;

    @PostMapping("/join")
    public ResponseEntity<Void> join(@RequestBody UserJoinRequest userJoinRequest) {

        userService.join(userJoinRequest);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{nickname}")
    public ResponseEntity<Boolean> nicknameVerify(@PathVariable String nickname) {

        return ResponseEntity.ok(userService.nicknameVerify(nickname));
    }

    @PutMapping
    public ResponseEntity<Void> modify(@AuthenticationPrincipal UserDetailsImpl user, @RequestBody UserModifyRequest userModifyRequest) {

        userService.modify(user, userModifyRequest);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserLoginRequest userLoginRequest, HttpServletResponse response) {
        UserEntity user = userService.login(userLoginRequest)
            .orElseThrow();
        TokenResponse token = jwtProvider.createTokenByLogin(userLoginRequest.getEmail());
        response.addHeader(JwtProvider.AUTHORIZATION_HEADER, token.getAccessToken());

        return ResponseEntity.ok(new LoginResponse(token.getAccessToken(), token.getRefreshToken(), user.getIsRecorded()));
    }

    @DeleteMapping("/logout")
    public ResponseEntity logout(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletRequest request){

        userService.logout(jwtProvider.resolveToken(request),userDetails.getUsername());

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<UserInfoResponse> getUserInfo(@RequestHeader("Authorization") String token) {

        String jwtToken = token.substring(7);

        return ResponseEntity.ok(userService.getUserInfo(jwtToken));
    }

    @GetMapping("/search")
    public ResponseEntity<UserInfoResponse> getUserInfoByEmail(@RequestParam String email) {

        log.debug("email : {}", email);
        return ResponseEntity.ok(userService.getUserInfoByEmail(email));
    }

    @GetMapping("/reissue")
    public ResponseEntity<TokenResponse> getToken(HttpServletRequest request) {

        return ResponseEntity.ok(userService.getToken(request));
    }

    @PostMapping ("/email/verification-request")
    public ResponseEntity<Void> mailSend(@RequestBody @Valid UserEmailRequest userEmailRequest){

        if (userService.isAuthenticated()) {
            throw new UserException(UserErrorCode.IS_AUTHENTICATED);
        }

        userEmailSendService.joinEmail(userEmailRequest.getEmail());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/email/verification")
    public ResponseEntity<Void> authCheck(@RequestBody @Valid UserEmailCheckRequest userEmailCheckRequest){

        Boolean checked=userEmailSendService.checkAuthNum(
                userEmailCheckRequest.getEmail(),
                userEmailCheckRequest.getAuthNum()
        );
        if(checked){
            return ResponseEntity.ok().build();
        }
        else{
            throw new UserException(UserErrorCode.CODE_NOT_SAME_ERROR);
        }
    }

    @PostMapping("/mbti")
    public ResponseEntity<MukBTIResponse> saveMukBTI(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody MukBTIRequest mukBTIRequest
            ){
        mukBTIService.save(userDetails.getEmail(), mukBTIRequest.getKey());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/mbti")
    public ResponseEntity<MukBTIResponse> findMukBTIResult(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        return ResponseEntity.ok(mukBTIService.getMukBTI(userDetails.getEmail()));
    }

}
