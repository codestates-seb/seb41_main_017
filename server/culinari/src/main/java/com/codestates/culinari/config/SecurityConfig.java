package com.codestates.culinari.config;

import com.codestates.culinari.config.security.filter.JwtAuthenticationFilter;
import com.codestates.culinari.config.security.filter.JwtVerificationFilter;
import com.codestates.culinari.config.security.handler.MemberAccessDeniedHandler;
import com.codestates.culinari.config.security.handler.MemberAuthenticationEntryPoint;
import com.codestates.culinari.config.security.handler.MemberAuthenticationFailureHandler;
import com.codestates.culinari.config.security.handler.MemberAuthenticationSuccessHandler;
import com.codestates.culinari.config.security.jwt.JwtTokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final JwtTokenizer jwtTokenizer;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin() // H2 웹 콘솔을 정상적으로 사용할 수 있도록 하는 역할
                .and()
                .csrf().disable() // csrf 공격에 대한 설정 비활성화
                .cors(withDefaults()) // withDefaults()일 경우 corsConfigurationSource 이름으로 등록된 Bean 이용
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 생성하지 않도록 설정
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new MemberAuthenticationEntryPoint())
                .accessDeniedHandler(new MemberAccessDeniedHandler())
                .and()
                .apply(new CustomFilterConfigurer()) // 직접 구현한 JwtAuthenticationFilter 등록
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        // User
                        .requestMatchers(HttpMethod.GET, "/users/username-check").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/email-check").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/**").hasRole("{authority=일반 유저}")
                        .requestMatchers(HttpMethod.PATCH, "/users").hasRole("{authority=일반 유저}")
                        .requestMatchers(HttpMethod.PATCH, "/users/password-edit").hasRole("{authority=일반 유저}")
                        .requestMatchers(HttpMethod.GET, "/logout").hasRole("{authority=일반 유저}")
                        .requestMatchers(HttpMethod.GET, "/mypage/**").hasRole("{authority=일반 유저}")
                        // Product 관련
                        .requestMatchers(HttpMethod.GET, "/collections/frequent").hasRole("{authority=일반 유저}")
                        .requestMatchers(HttpMethod.POST, "/product/**").hasRole("{authority=일반 유저}")
                        .requestMatchers(HttpMethod.PATCH, "/product/**").hasRole("{authority=일반 유저}")
                        .requestMatchers(HttpMethod.DELETE, "/product/**").hasRole("{authority=일반 유저}")
                        // Cart
                        .requestMatchers(HttpMethod.POST, "/carts").hasRole("{authority=일반 유저}")
                        .requestMatchers(HttpMethod.GET, "/carts").hasRole("{authority=일반 유저}")
                        .requestMatchers(HttpMethod.PATCH, "/carts/**").hasRole("{authority=일반 유저}")
                        .requestMatchers(HttpMethod.DELETE, "/carts/**").hasRole("{authority=일반 유저}")
                        // Order
                        .requestMatchers(HttpMethod.GET, "/orders/**").hasRole("{authority=일반 유저}")
                        // Payment 세부 먼저 큰거 나중
                        .requestMatchers(HttpMethod.POST, "/payments/**").hasRole("{authority=일반 유저}")
                        .requestMatchers(HttpMethod.GET, "/payments/success").permitAll()
                        .requestMatchers(HttpMethod.GET,"payments/fail").permitAll()
                        .requestMatchers(HttpMethod.GET,"/payments/**").hasRole("{authority=일반 유저}")
                        // CustomerCenter
                        .requestMatchers(HttpMethod.POST, "/board/inquiry/").hasRole("{authority=일반 유저}")
                        .requestMatchers(HttpMethod.GET, "/board/inquiry/").hasRole("{authority=일반 유저}")
                        .requestMatchers(HttpMethod.PATCH, "/board/inquiry/").hasRole("{authority=일반 유저}")
                        .requestMatchers(HttpMethod.DELETE, "/board/inquiry/").hasRole("{authority=일반 유저}")
                        .anyRequest().permitAll() // 모든 HTTP request 요청에 접근 허용
                );

        return http.build();
    }

    /**
     * PasswordEncoder Bean 객체 생성
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * CorsConfigurationSource Bean 생성(구체적인 CORS 정책 설정)
     * <p>
     * setAllowCredentials(true) : 크리덴셜 관련한 정보 허용?
     * Origins : 요청 호스트를 허용하는 역할
     * Methods : HTTP 메서드 허용(GET, POST, ...)
     * Headers : 볼 수 있다? 또는 어느 역할을 허용 한다 정도..?(정확하지 않음)
     * addExposedHeader : 클라이언트에서 헤더 정보를 저장하고 활용할 수 있도록 허락한다.
     * 메인 프로젝트 때에는 클라이언트에서 요구하는 정보가 더 있을 수 있다!
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true); // 쿠키가능.
        configuration.setAllowedOrigins(Arrays.asList(
                        "http://localhost:8080",
                        "http://localhost:3000",
                        "https://localhost:3000",
                "http://culinaribuild.s3-website.ap-northeast-2.amazonaws.com"
                )
        ); // * 은 문제 발생
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE", "UPDATE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.addExposedHeader("Authorization");
        configuration.addExposedHeader("Refresh");
        configuration.setMaxAge(3000L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); // UrlBasedCorsConfigurationSource 생성
        source.registerCorsConfiguration("/**", configuration); // 모든 URL에 앞에서 구성한 CORS 정책 적용

        return source;
    }

    /**
     * Custom Filter Configurer, JWTAuthenticationFilter를 등록하는 역할
     */
    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {

        /**
         * Configure 커스터마이징
         */
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            // AuthenticationManager 객체 얻기
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            // JwtAuthenticationFilter 생성 (AuthenticationManager와 JwtTokenizer DI)
            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);

            // 로그인 request URL
            jwtAuthenticationFilter.setFilterProcessesUrl("/users/signin");

            // 인증 성공 또는 실패 핸들러
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new MemberAuthenticationSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new MemberAuthenticationFailureHandler());

            // JwtVerificationFilter 생성
            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer);

            // Spring Security Filter Chain 추가
            builder.addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }
}
