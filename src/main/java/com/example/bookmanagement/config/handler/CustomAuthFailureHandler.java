package com.example.bookmanagement.config.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Objects;

/**
 * 사용자의 '인증'에 대해 실패하였을 경우 수행되는 Handler로 실패에 대한 반환값을 사용자에게 구성하여 전달하는 클래스
 * */
@Slf4j
public class CustomAuthFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        // 1. 클라이언트로 전달 할 응답 값을 구성.
        JSONObject jsonObject = new JSONObject();
        String failMsg = "";

        // 2. 발생한 Exception에 대해서 확인
        if (exception instanceof AuthenticationServiceException){
            failMsg = "로그인 정보가 일치하지 않습니다.";
        }
        else if (exception instanceof BadCredentialsException){
            failMsg = "로그인 정보가 일치하지 않습니다.";
        }
        else if(exception instanceof DisabledException){
            failMsg = "로그인 정보가 일치하지 않습니다.";
        }
        else if(exception instanceof AccountExpiredException){
            failMsg = "만료된 계정입니다.";
        }
        else if (exception instanceof CredentialsExpiredException){
            failMsg = "로그인 정보가 일치하지 않습니다.";
        }

        // 3. 응답 값을 구성하고 전달한다.
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();

        log.debug(failMsg);

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("userInfo", null);
        resultMap.put("resultCode", 9999);
        resultMap.put("failMsg", failMsg);
        jsonObject = new JSONObject(resultMap);

        printWriter.print(jsonObject);
        printWriter.flush();
        printWriter.close();
    }
}
