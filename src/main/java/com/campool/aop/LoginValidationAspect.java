package com.campool.aop;

import com.campool.service.AuthService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@RequiredArgsConstructor
@Aspect
@Component
public class LoginValidationAspect {

    @NonNull
    private final AuthService authService;

    @Before("@annotation(com.campool.annotation.LoginValidation)")
    public void loginValidate(JoinPoint joinPoint) {
        if (!authService.isValidAuthentication()) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "로그인이 필요한 서비스입니다.");
        }
    }
}
