package com.todostudy.config;

import com.todostudy.user.service.CustomUserDetailService; // CustomUserDetailsService 임포트
import com.todostudy.util.JwtUtil; // JwtUtil 임포트
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod; // HttpMethod는 필요 없으면 제거 가능
import org.springframework.security.config.Customizer; // Customizer는 필요 없으면 제거 가능
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; // 필요 없으면 제거 가능
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy; // SessionCreationPolicy 임포트
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter; // UsernamePasswordAuthenticationFilter 임포트
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 활성화 (이미 있다면 그대로 둡니다)
@RequiredArgsConstructor // Lombok: final 필드를 위한 생성자 자동 생성
public class SecurityConfig {

    // CustomUserDetailsService와 JwtUtil 주입
    private final CustomUserDetailService customUserDetailsService;
    private final JwtUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 보호 비활성화 (JWT 사용 시 일반적으로 비활성화)
                .csrf(AbstractHttpConfigurer::disable) // 람다식으로 변경

                // CORS 설정 (기존 설정을 유지)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 폼 로그인 비활성화 (JWT 사용 시 불필요)
                .formLogin(AbstractHttpConfigurer::disable)

                // HTTP Basic 인증 비활성화 (JWT 사용 시 불필요)
                .httpBasic(AbstractHttpConfigurer::disable)

                // 세션 관리 정책 설정: JWT는 STATELESS (무상태)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // HTTP 요청 인가 규칙 설정
                .authorizeHttpRequests(auth -> auth
                        // 회원가입과 로그인은 인증 없이 허용
                        .requestMatchers("/api/users/join", "/api/users/login").permitAll()
                        // 그 외 모든 요청은 인증 필요 (JWT 토큰이 필요)
                        .anyRequest().authenticated()
                )

                // JWT 인증 필터 추가: UsernamePasswordAuthenticationFilter 이전에 실행
                .addFilterBefore(new JwtAuthenticationFilter(jwtUtil, customUserDetailsService),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // 기존 CORS 설정 유지
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*")); // 모든 출처 허용 (개발 시 편리, 운영 시 특정 도메인으로 제한 권장)
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*")); // 모든 헤더 허용
        configuration.setAllowCredentials(true); // 자격 증명 허용 (쿠키, HTTP 인증 등)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 경로에 CORS 설정 적용
        return source;
    }

}