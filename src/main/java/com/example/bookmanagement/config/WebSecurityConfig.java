package com.example.bookmanagement.config;

import com.example.bookmanagement.config.filter.CustomAuthenticationFilter;
import com.example.bookmanagement.config.filter.JwtAuthorizationFilter;
import com.example.bookmanagement.config.handler.CustomAuthFailureHandler;
import com.example.bookmanagement.config.handler.CustomAuthSuccessHandler;
import com.example.bookmanagement.config.handler.CustomAuthenticationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * spring security 환경설정을 구성하기 위한 클래스이다.
 * 웹서비스가 로드될 때 Spring Container에 의해 관리가 되는 클래스이며,
 * 사용자에대한 '인증'과  '인가'에 대한 구성을 Bean 메서드로 주입한다.
 */

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    // 1. 정적자원(Resource)에 대해서 Security를 적용하지 않으로 설정
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    // 2. HTTP에 대해서 '인증'과 '인가'를 담당하는 메서드이며 필터를 통해 인증방식과 인증절차에 대해서 등록하며 설정을 담당하는 메서드.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


                // 서버에 인증정보를 저장하지 않기에 csrf를 사용하지 않는다.
                http.csrf(AbstractHttpConfigurer::disable);

                // form 기반의 로그인(Form Based Authentication)에 대해 비활성화
                http.formLogin(AbstractHttpConfigurer::disable);

                //HTTP Basic Authentication에 대해 비활성화
                http.httpBasic(AbstractHttpConfigurer::disable);

                // 토큰을 사용하는 경우 모든 요청에 대해 '인가' 적용 할 예정이기 때문에 모든 HTTP 요청에 대해 인증 없이 접근 허용
                http.authorizeHttpRequests((auth) -> auth.anyRequest().permitAll());

                // [Spring Security Custom filter] load - Form '인증'에 대해서 사용
                http.addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

                // Session 기반의 인증 기반을 사용하지 않고 추후 JWT를 이용해서 인증예정
                http.sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//                http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

                // [Spring Security JWT filter] load
//                .addFilterBefore(jwtAuthorizationFilter(), BasicAuthenticationFilter.class)

        return http.build();
    }

    // jwt 토큰을 통해서 사용자를 인증한다.
//    @Bean
//    public JwtAuthorizationFilter jwtAuthorizationFilter(){
//        return new JwtAuthorizaionFilter();
//    }

    // Spring Security 기반의 사용자 정보가 맞지 않는 경우 수행이 되며, 결과값을 리턴해주는 Handler
    @Bean
    public CustomAuthFailureHandler customLoginFailureHandler(){
        return new CustomAuthFailureHandler();
    }

    // Spring security 기반의 사용자 정보가 맞을 경우 수행이 되며, 결과값을 리턴해주는 Handler
    @Bean
    public CustomAuthSuccessHandler customLoginSuccessHandler(){
        return new CustomAuthSuccessHandler();
    }

    // 비밀번호를 암호화하기 위한 BCrypt 인코딩을 통하여 비밀번호에 대한 암호화 수행
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //'인증' 제공자로 사용자 이름과 비밀번호를 요구한다.
    // 과정: CustomAuthenticationFilter -> AuthenticationManager(interface) -> CustomAuthenticationProvider(implements)
    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider(){
        return new CustomAuthenticationProvider(bCryptPasswordEncoder());
    }

    // Authenticate의 인증 메서드를 제공하는 매니저로 'Provider'의 인터페이스를 의미
    // 과정: CustomAuthenticationFilter -> AuthenticationManager(interface) -> CustomAuthenticationProvider(implements)
    @Bean
    public AuthenticationManager authenticationManager(){
        return new ProviderManager(customAuthenticationProvider());
    }

    // Customized '인증' 을 수행한 필터로, 접근 URL, 데이터 전달방식(Form) 등 인증 과정 및 인증 후 처리에 대한 설정을 구성하는 메서드이다.
    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter(){
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        customAuthenticationFilter.setFilterProcessesUrl("/api/v1/user/login"); // 접근 url
        customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler()); // '인증' 성공시 해당 핸들러로 처리전가
        customAuthenticationFilter.setAuthenticationFailureHandler(customLoginFailureHandler()); // '인증' 실패시 해당 핸들러로 처리전가
        customAuthenticationFilter.afterPropertiesSet();
        return customAuthenticationFilter;
    }

}
