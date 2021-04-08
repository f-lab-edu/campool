package com.campool.service;

import com.campool.encrypt.Encryptor;
import com.campool.mapper.AdminMapper;
import com.campool.model.AdminSignUp;
import com.campool.model.UserSignUp;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {

    @NonNull
    private final AdminMapper adminMapper;

    @NonNull
    private final Encryptor encryptor;

    public void add(AdminSignUp adminSignUp) {
        if (isDuplicate(adminSignUp.getId())) {
            throw new DuplicateKeyException("중복된 아이디가 존재합니다.");
        } else {
            adminMapper.insert(adminSignUp.getEncryptedPasswordUserSignUp(encryptor));
        }
    }

    public boolean isDuplicate(String id) {
        return adminMapper.findById(id) != null;
    }

}
