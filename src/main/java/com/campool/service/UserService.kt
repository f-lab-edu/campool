package com.campool.service;

import com.campool.encrypt.Encryptor;
import com.campool.exception.NoSuchUserException;
import com.campool.mapper.UserMapper;
import com.campool.model.UserInfo;
import com.campool.model.UserSignUpRequest;
import com.campool.model.UserUpdateRequest;
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

    public void add(UserSignUpRequest userSignUpRequest) {
        if (isDuplicate(userSignUpRequest.getId())) {
            throw new DuplicateKeyException("중복된 아이디가 존재합니다.");
        } else {
            userMapper.insertUser(userSignUpRequest.getEncryptedPasswordUserSignUp(encryptor));
        }
    }

    public boolean isDuplicate(String id) {
        return userMapper.findById(id) != null;
    }

    public UserInfo getUserInfoById(String id) {
        return userMapper.findById(id);
    }

    public UserInfo getByIdAndPw(String id, String password) {
        UserInfo userInfo = userMapper.findByIdAndPassword(id, encryptor.encrypt(password));
        if (isValidUser(userInfo)) {
            return userInfo;
        } else {
            throw new NoSuchUserException("해당하는 사용자 정보가 없습니다.");
        }
    }

    private boolean isValidUser(UserInfo userInfo) {
        return userInfo != null;
    }

    public void updateById(String id, UserUpdateRequest userUpdateRequest) {
        //현재 비밀번호의 사용자가 존재하는지 확인
        getByIdAndPw(id, userUpdateRequest.getCurrentPassword());
        userMapper.updateById(id, encryptor.encrypt(userUpdateRequest.getNewPassword()),
                userUpdateRequest.getName(), userUpdateRequest.getEmail(),
                userUpdateRequest.getTelephone());
    }

    public void deleteById(String id) {
        if (isValidUser(userMapper.findById(id))) {
            userMapper.deleteById(id);
        } else {
            throw new NoSuchUserException("해당하는 사용자 정보가 없습니다.");
        }
    }

}
