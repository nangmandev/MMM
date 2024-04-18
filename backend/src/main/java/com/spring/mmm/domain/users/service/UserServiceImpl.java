package com.spring.mmm.domain.users.service;

import com.spring.mmm.common.config.RedisDao;
import com.spring.mmm.common.config.jwt.JwtProvider;
import com.spring.mmm.domain.mbtis.domain.MukBTIEntity;
import com.spring.mmm.domain.mbtis.domain.MukBTIResultEntity;
import com.spring.mmm.domain.mbtis.service.port.MukBTIRepository;
import com.spring.mmm.domain.mbtis.service.port.MukBTIResultRepository;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.mukgroups.service.port.MukboRepository;
import com.spring.mmm.domain.mukgroups.service.port.MukgroupRepository;
import com.spring.mmm.domain.users.controller.request.UserJoinRequest;
import com.spring.mmm.domain.users.controller.request.UserLoginRequest;
import com.spring.mmm.domain.users.controller.request.UserModifyRequest;
import com.spring.mmm.domain.users.controller.response.TokenResponse;
import com.spring.mmm.domain.users.controller.response.UserInfoResponse;
import com.spring.mmm.domain.users.exception.UserErrorCode;
import com.spring.mmm.domain.users.exception.UserException;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import com.spring.mmm.domain.users.infra.UserEntity;
import com.spring.mmm.domain.users.service.port.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final MukboRepository mukboRepository;
    private final MukgroupRepository mukgroupRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final MukBTIRepository mukBTIRepository;
    private final MukBTIResultRepository mukBTIResultRepository;
    private final RedisDao redisDao;


    @Override
    @Transactional
    public void join(UserJoinRequest userJoinRequest) {

        String email = userJoinRequest.getEmail();
        String nickname = userJoinRequest.getNickname();
        String password = userJoinRequest.getPassword();
        String encodedPW = passwordEncoder.encode(password);

        boolean isEmailExist = userRepository.existsByEmail(email);
        boolean isNicknameExist = userRepository.existsByNickname(nickname);

        if (isEmailExist | isNicknameExist) {
            return;
        }

        MukgroupEntity mukgroupEntity = MukgroupEntity.create(nickname, Boolean.TRUE);
        mukgroupRepository.save(mukgroupEntity);
        UserEntity user = UserEntity.create(userJoinRequest, encodedPW, mukgroupEntity.getMukgroupId());
        userRepository.create(user);

        List<MukBTIEntity> mukBTIEntities = mukBTIRepository.findAllMukBTI();
        List<MukBTIResultEntity> mukBTIResultEntities = new ArrayList<>();
        for(MukBTIEntity mukBTI : mukBTIEntities){
            mukBTIResultEntities.add(
                    MukBTIResultEntity.createByType(15, mukBTI, user.getMukboEntity(), user)
            );
        }
        mukBTIResultRepository.saveAll(mukBTIResultEntities);
    }

    @Override
    @Transactional
    public boolean nicknameVerify(String nickname) {

        boolean isNicknameExist = userRepository.existsByNickname(nickname);

        if (isNicknameExist) {
            return true;
        }

        return false;
    }

    @Override
    @Transactional
    public void modify(UserDetailsImpl user, UserModifyRequest userModifyRequest) {
        String email = user.getEmail();
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(
                () -> new UserException(UserErrorCode.INVALID_USER)
        );

        String nickname = userEntity.getNickname();

        if (userModifyRequest.getNickname() != null) {
            nickname = userModifyRequest.getNickname();
            boolean isNicknameExist = userRepository.existsByNickname(nickname);

            if (isNicknameExist) {
                throw new UserException(UserErrorCode.EXIST_NICKNAME);
            }
        }

        String password = userEntity.getPassword();

        if (userModifyRequest.getNewPassword() != null) {
            if (!passwordEncoder.matches(userModifyRequest.getPassword(), password)) {
                throw new UserException(UserErrorCode.PASSWORD_CONFLICT);
            }
            if (!userModifyRequest.getNewPassword().equals(userModifyRequest.getNewPasswordConfirm())) {
                throw new UserException(UserErrorCode.PASSWORD_CONFIRM_CONFLICT);
            }
            password = passwordEncoder.encode(userModifyRequest.getNewPassword());
        }

        userEntity.modify(nickname, password);
    }


    @Override
    @Transactional
    public Optional<UserEntity> login(UserLoginRequest userLoginRequest) {
        String email = userLoginRequest.getEmail();
        String password = userLoginRequest.getPassword();
        UserEntity user = userRepository.findByEmail(email).orElseThrow(
                () -> new UserException(UserErrorCode.INVALID_USER)
        );

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UserException(UserErrorCode.INVALID_USER);
        }
        return Optional.of(user);
    }

    @Override
    @Transactional
    public UserInfoResponse getUserInfo(String jwtToken) {

        String email = jwtProvider.getUserInfoFromToken(jwtToken);
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        Long userId = user.getId();
        String nickname = user.getNickname();

        return UserInfoResponse.of(userId, email, nickname);
    }

    @Override
    @Transactional
    public UserInfoResponse getUserInfoByEmail(String email) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        Long userId = user.getId();
        String nickname = user.getNickname();

        return UserInfoResponse.of(userId, email, nickname);
    }

    @Override
    public TokenResponse getToken(HttpServletRequest request) {
        String oldRefreshToken = request.getHeader("Authorization");
        String oldCompactToken = oldRefreshToken.substring(7);

        String email = jwtProvider.getUserInfoFromToken(oldCompactToken);
        log.debug("serviceImpl email: {}", email);

        return jwtProvider.reissueAtk(email, oldRefreshToken);
    }


    @Override
    @Transactional
    public void logout(String accessToken, String email) {
        if (redisDao.hasKey(email)) {
            redisDao.deleteRefreshToken(email);
        } else {
            throw new IllegalArgumentException("이미 로그아웃한 유저입니다.");
        }
    }

    @Override
    @Transactional
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !authentication.getPrincipal().equals("anonymousUser");
    }

}
