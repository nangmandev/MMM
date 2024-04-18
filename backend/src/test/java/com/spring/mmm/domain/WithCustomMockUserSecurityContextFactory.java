package com.spring.mmm.domain;

import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import com.spring.mmm.domain.users.infra.UserEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.List;

public class WithCustomMockUserSecurityContextFactory implements WithSecurityContextFactory<CustomMockUser> {
    @Override
    public SecurityContext createSecurityContext(CustomMockUser annotation) {
        String role = annotation.role();

        MukboEntity mukbo = TestFixture.mukboEntityGroup;
        UserEntity userEntity = UserEntity.builder()
                .mukboEntity(mukbo)
                .build();

        UserDetailsImpl user = UserDetailsImpl.builder()
                .email(annotation.email())
                .user(userEntity)
                .build();

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(user, "password", List.of(new SimpleGrantedAuthority(role)));
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(token);
        return context;
    }
}
