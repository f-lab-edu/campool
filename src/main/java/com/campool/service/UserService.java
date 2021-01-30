package com.campool.service;

import com.campool.auth.SessionAuth;
import com.campool.encrypt.Encryptor;
import com.campool.mapper.UserMapper;
import com.campool.model.UserLogin;
import com.campool.model.UserSignUp;
import javax.servlet.http.HttpSession;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    @NonNull
    private final UserMapper userMapper;

    @NonNull
    private final Encryptor encryptor;

    public void add(UserSignUp userSignUp) {
        if (isDuplicate(userSignUp.getId())) {
            throw new DuplicateKeyException("중복된 아이디가 존재합니다.");
        } else {
            userMapper.insert(userSignUp.getEncryptedPasswordUserSignUp(encryptor));
        }
    }

    public boolean isDuplicate(String id) {
        return userMapper.findById(id) != null;
    }

    public void login(UserLogin userLogin, HttpSession session) {
        UserSignUp userSignUp = userMapper
                .findByIdAndPassword(userLogin.getId(), encryptor.encrypt(userLogin.getPassword()));
        if (isAuthenticatedUser(userSignUp)) {
            SessionAuth.setAuthenticatedUser(userSignUp, session);
        } else {
            throw new RuntimeException(new NotFoundException("로그인에 실패했습니다."));
        }
    }

    public boolean isAuthenticatedUser(UserSignUp userSignUp) {
        return userSignUp != null;
    }

}
