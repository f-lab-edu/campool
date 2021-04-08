package com.campool.controller;

import com.campool.model.AdminSignUp;
import com.campool.service.AdminService;
import javax.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminController {

    @NonNull
    private final AdminService adminService;

    @PostMapping("/admins")
    public void signUpUser(@Valid AdminSignUp adminSignUp) {
        adminService.add(adminSignUp);
    }

}
