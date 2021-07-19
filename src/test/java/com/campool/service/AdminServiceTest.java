package com.campool.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import com.campool.encrypt.Encryptor;
import com.campool.exception.NoSuchUserException;
import com.campool.mapper.AdminMapper;
import com.campool.model.AdminInfo;
import com.campool.model.AdminSignUpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    AdminSignUpRequest adminSignUpRequest;

    AdminInfo adminInfo;

    @BeforeEach
    void setUp() {
        adminSignUpRequest = new AdminSignUpRequest(
                "adminId",
                "adminPassword",
                "adminName",
                "admin@email.co.kr",
                "01012341234");

        adminInfo = new AdminInfo(
                "adminId",
                "adminName",
                "admin@email.co.kr",
                "01012341234");
    }

    @DisplayName("중복된 ID의 관리자 추가 요청은 중복 예외를 발생")
    @Test
    void addDuplicateAdminIdThrowsException() {
        AdminSignUpRequest duplicateAdmin = new AdminSignUpRequest(
                "adminId",
                "adminPassword",
                "adminName",
                "admin@email.co.kr",
                "01012341234");
        given(adminMapper.findById(adminSignUpRequest.getId())).willReturn(adminInfo);

        Exception exception = assertThrows(DuplicateKeyException.class,
                () -> adminService.add(duplicateAdmin));

        assertEquals("중복된 아이디가 존재합니다.", exception.getMessage());
    }

    @DisplayName("존재하는 관리자 ID, PASSWORD로 요청 시 해당 관리자 정보를 담은 객체를 반환")
    @Test
    void findByExistentAdminIdAndPw() {
        String id = "adminId";
        String password = "adminPassword";

        given(encryptor.encrypt(adminSignUpRequest.getPassword())).willReturn(ENCRYPTED_PASSWORD);
        given(adminMapper.findByIdAndPassword(adminSignUpRequest.getId(),
                encryptor.encrypt(adminSignUpRequest.getPassword()))).willReturn(adminInfo);

        assertNotNull(adminService.getByIdAndPw(id, password));
    }

    @DisplayName("존재하지 않는 관리자 ID, PASSWORD로 요청 시 예외 발생")
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