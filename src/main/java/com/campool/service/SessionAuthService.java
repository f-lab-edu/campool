package com.campool.service;

import com.campool.exception.NoSuchUserException;
import com.campool.model.UserLoginRequest;
import com.campool.model.UserSignUp;
import javax.servlet.http.HttpSession;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

@RequiredArgsConstructor
@Service
public class SessionAuthService implements AuthService {

    public static final String AUTH_USER_KEY = "userId";

    @NonNull
    private final UserService userService;

    @NonNull
    private final HttpSession session;

    @Override
    public void authenticate(UserLoginRequest userLoginRequest) {
        UserSignUp userSignUp = userService
                .getByIdAndPw(userLoginRequest.getId(), userLoginRequest.getPassword());
        session.setAttribute(AUTH_USER_KEY, userSignUp.getId());
    }

    @Override
    public void deauthenticate() {
        if (isValidAuthentication()) {
            session.invalidate();
        } else {
            throw new NoSuchUserException("로그아웃할 사용자 세션 정보가 없습니다.");
        }
    }

    @Override
    public boolean isValidAuthentication() {
        return session.getAttribute(AUTH_USER_KEY) != null;
    }

}
