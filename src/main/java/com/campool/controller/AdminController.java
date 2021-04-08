package com.campool.controller;

import com.campool.annotation.LoginValidation;
import com.campool.model.AdminLoginRequest;
import com.campool.model.AdminSignUp;
import com.campool.service.AdminService;
import com.campool.service.AuthService;
import javax.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminController {

    @NonNull
    private final AdminService adminService;

    @NonNull
    private final AuthService authService;

    @PostMapping("/admins")
    public void signUpAdmin(@Valid AdminSignUp adminSignUp) {
        adminService.add(adminSignUp);
    }

    @PostMapping("/admins/login")
    public void loginAdmin(@Valid AdminLoginRequest adminLoginRequest) {
        authService.authenticate(adminLoginRequest);
    }

    @LoginValidation
    @GetMapping("/users/logout")
    public void logoutAdmin() {
        authService.deauthenticate();
    }

}
