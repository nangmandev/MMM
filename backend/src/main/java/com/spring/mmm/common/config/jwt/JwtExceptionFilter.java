package com.spring.mmm.common.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.mmm.common.exception.ErrorResponse;
import com.spring.mmm.domain.users.exception.UserErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");

        try {
            filterChain.doFilter(request, response);
        } catch (Throwable e) {
            log.debug("에러종류 : {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");

            ObjectMapper objectMapper = new ObjectMapper();
            String result = objectMapper.writeValueAsString(ErrorResponse.createErrorResponse(UserErrorCode.TOKEN_EXPIRED, request.getRequestURI()));
            response.getWriter().write(result);

        }
    }
}