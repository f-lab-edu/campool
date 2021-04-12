package com.campool.aop;

import com.campool.annotation.LoginValidation;
import com.campool.service.AuthService;
import java.lang.reflect.Method;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
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

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LoginValidation loginValidation = method.getAnnotation(LoginValidation.class);

        int necessaryRole = loginValidation.authorization().getOrder();
        int currentRole = authService.getAuthorization().getOrder();

        if (currentRole < necessaryRole) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "해당 권한은 접근할 수 없습니다.");
        }
    }
}
