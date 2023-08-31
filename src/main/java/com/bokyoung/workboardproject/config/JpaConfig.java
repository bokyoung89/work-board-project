package com.bokyoung.workboardproject.config;

import com.bokyoung.workboardproject.dto.security.BoardPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Security;
import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext()) // security에 대한 모든 정보를 들고 있는 클래스
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated) //인증되었는지, 로그인한 상태인지 확인
                .map(Authentication::getPrincipal)//로그인 정보 꺼내옴
                .map(BoardPrincipal.class::cast)
                .map(BoardPrincipal::getUsername); //실제 유저 정보를 불러옴
    }
}
