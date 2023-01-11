package com.codestates.culinari.config.security.handler;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.config.security.dto.CustomUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

/**
 * 성공 시 추가 작업을 할 수 있 는 핸들러
 */
@Slf4j
public class MemberAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        log.info("MemberAuthenticationSuccessHandler auth.getPrincipal : {}", authentication.getPrincipal().toString());

        CustomPrincipal customPrincipal = CustomPrincipal.of(
                customUserDetails.username(),
                customUserDetails.userId(),
                customUserDetails.profileId()
                );
        String principalToJson = mapper.writeValueAsString(customPrincipal);
        response.setContentType("application/json");
        response.getWriter().write(principalToJson);

        log.info("로그인 인증 성공");
    }
}
