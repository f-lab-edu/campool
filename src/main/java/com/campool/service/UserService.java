package com.campool.service;

import com.campool.mapper.UserMapper;
import com.campool.model.User;
import com.campool.utils.SHA256Utility;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    @NonNull
    private final UserMapper userMapper;

    public void add(User user) {
        if (userMapper.findById(user.getId()) == null) {
            user.setPassword(SHA256Utility.encrypt(user.getPassword()));
            userMapper.insert(user);
        } else {
            throw new RuntimeException("중복된 아이디가 존재합니다.");
        }
    }

}
