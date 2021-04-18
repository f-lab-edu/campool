package com.campool.controller;

import com.campool.service.AuthService;
import com.campool.service.GearTypeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GearTypeController.class)
class GearTypeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GearTypeService gearTypeService;

    @MockBean
    AuthService authService;

    @DisplayName("올바른 타입 추가 요청 시 200 상태 코드를 반환")
    @Test
    void registerGearTypeHasSuccess() throws Exception {
        this.mockMvc.perform(
                post("/types")
                        .param("name", "gearTypeName"))
                .andExpect(status().isOk());
    }

    @DisplayName("255자를 초과한 타입 추가 요청 시 400 상태 코드를 응답")
    @Test
    void registerGearTypeOver255HasError() throws Exception {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            builder.append("a");
        }

        String gearTypeNameOver255 = builder.toString();

        this.mockMvc.perform(
                post("/types")
                        .param("name", gearTypeNameOver255))
                .andExpect(status().is4xxClientError());
    }

}