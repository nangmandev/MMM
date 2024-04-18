package com.spring.mmm.domain.users.service.port;

import com.spring.mmm.domain.users.infra.UserEntity;

import java.util.Optional;

public interface UserRepository {

    Optional<UserEntity> create(UserEntity user);
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
}
