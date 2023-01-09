package com.codestates.culinari.config.security.filter;

import com.codestates.culinari.config.security.dto.CustomUserDetails;
import com.codestates.culinari.config.security.jwt.JwtTokenizer;
import com.codestates.culinari.user.dto.request.SignInDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenizer jwtTokenizer;

    /**
     * 인증을 시도하는 메서드
     */
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        ObjectMapper objectMapper = new ObjectMapper();
        SignInDto loginDto = objectMapper.readValue(request.getInputStream(), SignInDto.class);

        log.info("로그인을 시도합니다 : {}", loginDto);
        // email Password 정보를 포함한 토큰 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password());

        // AuthenticationManager에게 인증 위임
        return authenticationManager.authenticate(authenticationToken);
    }

    /**
     * 인증에 성공할 경우 호출되는 메서드
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain filterChain,
                                            Authentication authResult) throws ServletException, IOException {
        CustomUserDetails customUserDetails = (CustomUserDetails) authResult.getPrincipal(); // Member 엔티티 객체 얻기
        log.info("authResult : {}", authResult.getPrincipal().toString());
        String accessToken = delegateAccessToken(customUserDetails); // Access Token 생성
        String refreshToken = delegateRefreshToken(customUserDetails); // Refresh Token 생성

        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("Refresh", refreshToken);

        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
    }

    /**
     * Access Token 생성 메서드
     */
    private String delegateAccessToken(CustomUserDetails customUserDetails) {
        // 인증된 사용자와 관련된 정보 추가
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", customUserDetails.username());
        claims.put("userId", customUserDetails.userId());
        claims.put("profileId", customUserDetails.profileId());
        claims.put("roles", customUserDetails.authorities());

        String subject = customUserDetails.getUsername(); // JWT 제목
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes()); // 토큰 발행 일자
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey()); // Secret Key 문자열 인코딩

        // Access Token 생성
        return jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);
    }

    /**
     * Refresh Token 생성 메서드
     */
    private String delegateRefreshToken(CustomUserDetails customUserDetails) {
        String subject = customUserDetails.getUsername();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        // Refresh Token 생성
        return jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);
    }
}
