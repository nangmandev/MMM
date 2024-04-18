package com.spring.mmm.common.config.jwt;

import com.spring.mmm.common.config.RedisDao;
import com.spring.mmm.domain.users.controller.response.TokenResponse;
import com.spring.mmm.domain.users.exception.UserErrorCode;
import com.spring.mmm.domain.users.exception.UserException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

    // Header KEY 값
    public static final String AUTHORIZATION_HEADER = "Authorization";
    // Token 식별자
    private static final String BEARER_PREFIX = "Bearer ";

    private static final long ACCESS_TOKEN_TIME =
        1000 * 60 * 30L; // 30 분 1000ms(=1s) * 60(=1min) * 30 (=30min)
    private static final long REFRESH_TOKEN_TIME = 1000 * 60 * 60 * 24 * 7L; // 7일
    public static final String CLAIM_KEY = "email";

    @Value("${jwt.secret}")
    private String secretKey;

    //HMAC-SHA 키를 생성
    private Key key;
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;


    private final UserDetailsService userDetailsService;
    private final RedisDao redisDao;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }


    private String createToken(String email, Long tokenExpireTime) {
        Date date = new Date();
        return BEARER_PREFIX + Jwts.builder()
                .claim(CLAIM_KEY, email)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + tokenExpireTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public TokenResponse createTokenByLogin(String email) {
        String accessToken = createToken(email, ACCESS_TOKEN_TIME);
        String refreshToken = createToken(email,  REFRESH_TOKEN_TIME);
        redisDao.setRefreshToken(email, refreshToken, REFRESH_TOKEN_TIME);
        return TokenResponse.create(accessToken, refreshToken);
    }

    public TokenResponse reissueAtk(String email, String reToken) {
        // 레디스 저장된 리프레쉬토큰값을 가져와서 입력된 reToken 같은지 유무 확인
        if (!redisDao.getRefreshToken(email).equals(reToken)) {
            throw new UserException(UserErrorCode.INVALID_USER);
        }
        String accessToken = createToken(email, ACCESS_TOKEN_TIME);
        String refreshToken = createToken(email, REFRESH_TOKEN_TIME);
        redisDao.setRefreshToken(email, refreshToken, REFRESH_TOKEN_TIME);
        return TokenResponse.create(accessToken, refreshToken);
    }

    public String getUserInfoFromToken(String token){

        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.get(CLAIM_KEY, String.class);

    }

    public boolean validateToken(String token) {
        Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);
        return true;
    }


    public Authentication createUserAuthentication(String email) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}
