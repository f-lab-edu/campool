package com.campool.aop;

import com.campool.annotation.PresentUserId;
import com.campool.service.AuthService;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Aspect
@Component
public class PresentUserIdResolver {

    @NonNull
    private final AuthService authService;

    @Around("execution(* *(.., @com.campool.annotation.PresentUserId (*), ..))")
    public Object resolveId(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Class<?>[] parameterTypes = method.getParameterTypes();
        loop:
        for (int i = 0; i < args.length; i++) {
            for (Annotation annotation : parameterAnnotations[i]) {
                if (annotation.annotationType() == PresentUserId.class
                        && parameterTypes[i] == String.class) {
                    args[i] = authService.getAuthenticatedUserId();
                    break loop;
                }
            }
        }
        return joinPoint.proceed(args);
    }
}
