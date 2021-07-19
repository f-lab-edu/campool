package com.campool.controller;

import com.campool.model.AdminSignUpRequest;
import com.campool.service.AdminService;
import com.campool.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AdminController.class)
class AdminControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AdminService adminService;

    @MockBean
    AuthService authService;

    AdminSignUpRequest correctAdmin, incorrectAdmin;

    @BeforeEach
    void setUp() {
        correctAdmin = new AdminSignUpRequest(
                "adminId",
                "adminPassword",
                "adminName",
                "admin@email.co.kr",
                "01012341234");

        incorrectAdmin = new AdminSignUpRequest(
                "adminIdOver12Size",
                "adminPasswordOver20Size",
                "adminNameOver20Size@@@@",
                "adminemailcokr",
                "010-1234-1234");
    }

    @DisplayName("올바른 양식의 관리자 가입 요청은 200 상태 코드를 반환")
    @Test
    void signUpAdminCorrectFormSuccess() throws Exception {
        this.mockMvc.perform(
                post("/admins")
                        .param("id", correctAdmin.getId())
                        .param("password", correctAdmin.getPassword())
                        .param("name", correctAdmin.getName())
                        .param("email", correctAdmin.getEmail())
                        .param("telephone", correctAdmin.getTelephone()))
                .andExpect(status().isOk());
    }

    @DisplayName("올바르지 않은 양식의 관리자 가입 요청 시 400 상태 코드를 응답")
    @Test
    void signUpAdminIncorrectFormHasError() throws Exception {
        this.mockMvc.perform(
                post("/admins")
                        .param("id", incorrectAdmin.getId())
                        .param("password", incorrectAdmin.getPassword())
                        .param("name", incorrectAdmin.getName())
                        .param("email", incorrectAdmin.getEmail())
                        .param("telephone", incorrectAdmin.getTelephone()))
                .andExpect(status().is4xxClientError());
    }

    @DisplayName("올바른 양식의 ID와 PASSWORD를 전송하면 200 상태 코드 응답")
    @Test
    void loginAdminCorrectForm() throws Exception {
        this.mockMvc.perform(
                post("/admins/login")
                        .param("id", correctAdmin.getId())
                        .param("password", correctAdmin.getPassword()))
                .andExpect(status().isOk());
    }

    @DisplayName("올바르지 않은 양식의 ID와 PASSWORD를 전송하면 400 상태 코드를 응답")
    @Test
    void loginAdminIncorrectForm() throws Exception {
        this.mockMvc.perform(
                post("/admins/login")
                        .param("id", incorrectAdmin.getId())
                        .param("password", incorrectAdmin.getPassword()))
                .andExpect(status().is4xxClientError());
    }

}