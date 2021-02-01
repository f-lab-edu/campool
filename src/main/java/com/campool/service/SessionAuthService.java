package com.campool.service;

import com.campool.model.UserSignUp;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SessionAuthService implements AuthService {

    public static final String AUTH_USER_KEY = "userSignUp";

    private final HttpSession session;

    @Override
    public void authenticate(UserSignUp userSignUp) {
        session.setAttribute(AUTH_USER_KEY, userSignUp);
    }

    @Override
    public boolean isValidAuthentication() {
        return session.getAttribute(AUTH_USER_KEY) != null;
    }

}
