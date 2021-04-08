package com.campool.service;

import com.campool.encrypt.Encryptor;
import com.campool.exception.NoSuchUserException;
import com.campool.mapper.AdminMapper;
import com.campool.model.AdminSignUp;
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
        }
        adminMapper.insert(adminSignUp.getEncryptedPasswordUserSignUp(encryptor));
    }

    public boolean isDuplicate(String id) {
        return adminMapper.findById(id) != null;
    }

    public AdminSignUp getByIdAndPw(String id, String password) {
        AdminSignUp adminSignUp = adminMapper.findByIdAndPassword(id, encryptor.encrypt(password));
        if (isValidUser(adminSignUp)) {
            return adminSignUp;
        } else {
            throw new NoSuchUserException("해당하는 관리자 정보가 없습니다.");
        }
    }

    private boolean isValidUser(AdminSignUp adminSignUp) {
        return adminSignUp != null;
    }

}
