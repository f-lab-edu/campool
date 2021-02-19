package com.campool.interceptor;

import com.campool.service.AuthService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Component
public class LoginValidationInterceptor implements HandlerInterceptor {

    @NonNull
    private final AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler)
            throws IOException {
        if (authService.isValidAuthentication()) {
            return true;
        } else {
            response.sendError(403, "로그인이 필요한 서비스입니다.");
            return false;
        }
    }

}
