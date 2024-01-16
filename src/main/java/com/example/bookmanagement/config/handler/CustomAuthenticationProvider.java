package com.example.bookmanagement.config.handler;

import com.example.bookmanagement.web.dto.UserDetailsDto;
import jakarta.annotation.Resource;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 전달받은 사용자의 아이디와 비밀번호를 기반으로 비즈니스로직을 처리하여 사용자의 '인증'에 대해서 검증을 수행하는 클래스
 * CustomAuthenticationFilter로 부터 생성한 토큰을 통하여 'UserDetailsService'를 통해 DB 내에서 정보를 조회한다.
 * */
@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Resource
    private UserDetailsService userDetailsService;

    @NonNull
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("CustomAuthenticationProvider");
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        //AuthenticationFilter 에서 생성된 토큰으로부터 아이디와 비밀번호를 조회함.
        String userId = token.getName();
        String userPw = (String) token.getCredentials();

        // Spring Security - UserDetailsService를 통해 DB에서 아이디로 사용자 조회
        UserDetailsDto userDetailsDto = (UserDetailsDto) userDetailsService.loadUserByUsername(userId);

        if(!(userDetailsDto.getUserId().equalsIgnoreCase(userId) && userDetailsDto.getUserPw().equalsIgnoreCase(userPw)))
            throw new BadCredentialsException("Invalid password: " + userDetailsDto.getUserId());

        return new UsernamePasswordAuthenticationToken(userDetailsDto, userPw, userDetailsDto.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
