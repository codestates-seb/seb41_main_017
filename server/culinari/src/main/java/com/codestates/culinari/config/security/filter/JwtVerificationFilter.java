package com.codestates.culinari.config.security.filter;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.config.security.jwt.JwtTokenizer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * JWT 검증 필터
 */
@Slf4j
@RequiredArgsConstructor
public class JwtVerificationFilter extends OncePerRequestFilter {

    private final JwtTokenizer jwtTokenizer;

    /**
     * JWT 토큰 검증 및 SecurityContext에 Authentication 저장하는 메서드
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info("JWT 토큰 검증 및 SecurityContent에 저장을 시도합니다.");
        try {
            Map<String, Object> claims = verifyJws(request); // JWT 검증
            setAuthenticationToContext(claims); // SecurityContext에 Authentication 저장
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }

    /**
     * 특정 조건에 부합하면 동작을 수행하지 않고 건너뛰게 해주는 필터
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // 요청 헤더 정보 얻기
        String authorization = request.getHeader("Authorization");

        // 요청 헤더가 Bearer로 시작하지 않는다면 Filter 동작을 수행하지 않음.(비회원도 요청할 수 있다면)
        return authorization == null || !authorization.startsWith("Bearer");
    }

    /**
     * 요청에 대한 JWT 검증 메서드
     */
    private Map<String, Object> verifyJws(HttpServletRequest request) {
        // request의 header에서 JWT를 얻음
        String jws = request.getHeader("Authorization").replace("Bearer ", "");

        // Secret Key 획득
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        // JWT에서 claims가 정상적으로 파싱되면 검증 성공
        log.info("JWT에서 claims가 정상적으로 파싱 검증 성공했습니다. {}", jwtTokenizer.getClaims(jws, base64EncodedSecretKey).getBody());
        return jwtTokenizer.getClaims(jws, base64EncodedSecretKey).getBody();
    }


    /**
     * SecurityContext에 저장하기 위한 메서드
     */
    private void setAuthenticationToContext(Map<String, Object> claims) {
        for (Map.Entry<String, Object> entrySet : claims.entrySet()) {
            log.info(entrySet.getKey() + " : " + entrySet.getValue());
        }

        // username을 얻음
        String username = (String) claims.get("username");
        // Users id를 얻음
        Long userId = Long.valueOf((Integer) claims.get("userId"));
        // profile id 를 얻음
        Long profileId = Long.valueOf((Integer) claims.get("profileId"));
        // 권한 정보를 얻음
        Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>) ((List) claims.get("roles")).stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toUnmodifiableSet());

        log.info("authorities : {} ", authorities);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                CustomPrincipal.of(username, userId, profileId), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
