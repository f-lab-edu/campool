package com.campool.controller;

import com.campool.model.AdminSignUp;
import com.campool.service.AdminService;
import com.campool.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
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

    AdminSignUp correctAdmin, incorrectAdmin;

    @BeforeEach
    void setUp() {
        correctAdmin = new AdminSignUp(
                "adminId",
                "adminPassword",
                "adminName",
                "admin@email.co.kr",
                "01012341234");

        incorrectAdmin = new AdminSignUp(
                "adminIdOver12Size",
                "adminPasswordOver20Size",
                "adminNameOver20Size@@@@",
                "admin@emailcokr",
                "010-1234-1234");
    }

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

    @Test
    void loginAdminCorrectForm() throws Exception {
        this.mockMvc.perform(
                post("/admins/login")
                        .param("id", correctAdmin.getId())
                        .param("password", correctAdmin.getPassword()))
                .andExpect(status().isOk());
    }

    @Test
    void loginAdminIncorrectForm() throws Exception {
        this.mockMvc.perform(
                post("/admins/login")
                        .param("id", incorrectAdmin.getId())
                        .param("password", incorrectAdmin.getPassword()))
                .andExpect(status().is4xxClientError());
    }

}