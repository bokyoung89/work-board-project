package com.bokyoung.workboardproject.config;

import com.bokyoung.workboardproject.domain.UserAccount;
import com.bokyoung.workboardproject.repository.UserAccountRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@Import(SecurityConfig.class)
public class TestSecurityConfig {

    @MockBean private UserAccountRepository userAccountRepository;

    @BeforeTestMethod //각 테스트 메소드가 실행되기 직전에 이 인증 정보를 넣어줌. spring 환경에서 사용 가능.
    public void securitySetUp() { //userAccountRepository를 통해서 findById를 하면 Optional로 포장된 UserAccount 도메인 정보를 받는다.
        given(userAccountRepository.findById(anyString())).willReturn(Optional.of(UserAccount.of(
                "bboTest",
                "pw",
                "bbo-test@email.com",
                "boo-test",
                "test-memo"
        )));
    }
}
