package com.example.bookmanagement.config.filter;

import com.example.bookmanagement.web.dto.UserDto;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

/**
 * customized authentication(인증)
 * 해당 메서드 내에서 AuthenticationManager를 호출하여 전달한다.
 *
 * 인증 성공시 CustomAuthSuccessHandler를 호출하고, 실패시 CustomAuthFailureHandler를 호출
 *
 * 아이디와 비밀번호 데이터를 Form 데이터로 전송받아 '인증'을 담당하는 필터
 *
 * */
@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager){
        super.setAuthenticationManager(authenticationManager);
    }

    /**
     * Request 로 받은 ID와 패스워드를 기반으로 토큰을 발급한다.
     * */
    private UsernamePasswordAuthenticationToken getAuthRequest(HttpServletRequest request) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        UserDto user = objectMapper.readValue(request.getInputStream(), UserDto.class);
        log.debug("CustomAuthenticationFilter :: userId: {} userPw: {}", user.getUserId(), user.getUserPw());

        //ID와 패스워드를 기반으로 토큰 발급
        return new UsernamePasswordAuthenticationToken(user.getUserId(), user.getUserPw());
    }


    /**
     * 지정된 url로 form 전송을 하였을 경우, 파라미터 정보를 가지고 온다.
     * */
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        UsernamePasswordAuthenticationToken authRequest;
        try {
            authRequest = getAuthRequest(request);
            setDetails(request, authRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
