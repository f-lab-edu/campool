package com.campool.service;

import com.campool.encrypt.Encryptor;
import com.campool.mapper.UserMapper;
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

}
