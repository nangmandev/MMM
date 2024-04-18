package com.spring.mmm.domain;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithCustomMockUserSecurityContextFactory.class)
public @interface CustomMockUser{
    String email() default "ssafy@ssafy.com";
    String role() default "USER";
}
