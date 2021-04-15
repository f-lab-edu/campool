package com.campool.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import com.campool.encrypt.Encryptor;
import com.campool.exception.NoSuchUserException;
import com.campool.mapper.AdminMapper;
import com.campool.model.AdminSignUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    static final String ENCRYPTED_PASSWORD = "encryptedAdminPassword";

    @InjectMocks
    AdminService adminService;

    @Mock
    AdminMapper adminMapper;

    @Mock
    Encryptor encryptor;

    AdminSignUp adminSignUp;

    @BeforeEach
    void setUp() {
        adminSignUp = new AdminSignUp(
                "adminId",
                "adminPassword",
                "adminName",
                "admin@email.co.kr",
                "01012341234");
    }

    @Test
    void addDuplicateAdminIdThrowsException() {
        AdminSignUp duplicateAdmin = new AdminSignUp(
                "adminId",
                "adminPassword",
                "adminName",
                "admin@email.co.kr",
                "01012341234");
        given(adminMapper.findById(adminSignUp.getId())).willReturn(adminSignUp);

        Exception exception = assertThrows(DuplicateKeyException.class,
                () -> adminService.add(duplicateAdmin));

        assertEquals("중복된 아이디가 존재합니다.", exception.getMessage());
    }

    @Test
    void findByExistentAdminIdAndPw() {
        String id = "adminId";
        String password = "adminPassword";

        given(encryptor.encrypt(adminSignUp.getPassword())).willReturn(ENCRYPTED_PASSWORD);
        given(adminMapper.findByIdAndPassword(adminSignUp.getId(),
                encryptor.encrypt(adminSignUp.getPassword()))).willReturn(adminSignUp);

        assertNotNull(adminService.getByIdAndPw(id, password));
    }

    @Test
    void findByNonExistentAdminIdAndPwThrowsException() {
        String id = "id";
        String password = "password";

        given(encryptor.encrypt(password)).willReturn(ENCRYPTED_PASSWORD);
        given(adminMapper.findByIdAndPassword(id, encryptor.encrypt(password))).willReturn(null);

        Exception exception = assertThrows(NoSuchUserException.class,
                () -> adminService.getByIdAndPw(id, password));

        assertEquals("해당하는 관리자 정보가 없습니다.", exception.getMessage());
    }

}