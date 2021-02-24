package com.campool.resolver;

import com.campool.annotation.PresentUserId;
import com.campool.service.AuthService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
public class PresentUserIdResolver implements HandlerMethodArgumentResolver {

    @NonNull
    private final AuthService authService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isString = parameter.getParameterType().equals(String.class);
        if (!isString) {
            throw new IllegalArgumentException("String 타입의 매개 변수에만 사용 가능합니다.");
        }
        return parameter.getParameterAnnotation(PresentUserId.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return authService.getAuthenticatedUserId();
    }
}
