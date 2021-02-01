package com.campool.service;

import com.campool.encrypt.Encryptor;
import com.campool.exception.NoSuchUserException;
import com.campool.mapper.UserMapper;
import com.campool.model.UserLoginRequest;
import com.campool.model.UserSignUp;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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

    public UserSignUp getByUserLogin(UserLoginRequest userLoginRequest) {
        UserSignUp userSignUp = userMapper
                .findByIdAndPassword(userLoginRequest.getId(), encryptor.encrypt(userLoginRequest.getPassword()));
        if(isValidUser(userSignUp)) {
            return userSignUp;
        } else {
            throw new NoSuchUserException("해당하는 사용자 정보가 없습니다.");
        }
    }

    public boolean isValidUser(UserSignUp userSignUp) {
        return userSignUp != null;
    }

}
