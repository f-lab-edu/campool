package com.campool.controller

import com.campool.annotation.LoginValidation
import com.campool.enumeration.Role
import com.campool.model.AdminLoginRequest
import com.campool.model.AdminSignUpRequest
import com.campool.service.AdminService
import com.campool.service.AuthService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class AdminController(
    private val adminService: AdminService,
    private val authService: AuthService
) {
    @PostMapping("/admins")
    fun signUpAdmin(@Valid adminSignUp: AdminSignUpRequest) {
        adminService.add(adminSignUp)
    }

    @PostMapping("/admins/login")
    fun loginAdmin(@Valid request: AdminLoginRequest) {
        authService.authenticate(request)
    }

    @LoginValidation(role = Role.ADMIN)
    @GetMapping("/admins/logout")
    fun logoutAdmin() {
        authService.deauthenticate()
    }
}