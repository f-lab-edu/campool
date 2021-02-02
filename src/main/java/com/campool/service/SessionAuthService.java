package com.campool.service;

import com.campool.encrypt.Encryptor;
import com.campool.exception.NoSuchUserException;
import com.campool.mapper.UserMapper;
import com.campool.model.UserSignUp;
import javax.servlet.http.HttpSession;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SessionAuthService implements AuthService {

    public static final String AUTH_USER_KEY = "userSignUp";

    @NonNull
    private final UserMapper userMapper;

    @NonNull
    private final Encryptor encryptor;

    @NonNull
    private final HttpSession session;

    @Override
    public UserSignUp getByIdAndPw(String id, String password) {
        UserSignUp userSignUp = userMapper.findByIdAndPassword(id, encryptor.encrypt(password));
        if (isValidUser(userSignUp)) {
            return userSignUp;
        } else {
            throw new NoSuchUserException("해당하는 사용자 정보가 없습니다.");
        }
    }

    @Override
    public void authenticate(UserSignUp userSignUp) {
        session.setAttribute(AUTH_USER_KEY, userSignUp);
    }

    @Override
    public boolean isValidAuthentication() {
        return session.getAttribute(AUTH_USER_KEY) != null;
    }

    private boolean isValidUser(UserSignUp userSignUp) {
        return userSignUp != null;
    }

}
