package com.campool.controller

import com.campool.annotation.LoginUserId
import com.campool.annotation.LoginValidation
import com.campool.model.UserInfo
import com.campool.model.UserLoginRequest
import com.campool.model.UserSignUpRequest
import com.campool.model.UserUpdateRequest
import com.campool.service.AuthService
import com.campool.service.UserService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class UserController(
    private val userService: UserService,
    private val authService: AuthService
) {
    @PostMapping("/users")
    fun signUpUser(@Valid request: UserSignUpRequest) {
        userService.add(request)
    }

    @PostMapping("/users/login")
    fun loginUser(request: UserLoginRequest) {
        authService.authenticate(request)
    }

    @LoginValidation
    @GetMapping("/my-infos")
    fun getUserInfo(@LoginUserId id: String): UserInfo? {
        return userService.getUserInfoById(id)
    }

    @LoginValidation
    @GetMapping("/users/logout")
    fun logoutUser() {
        authService.deauthenticate()
    }

    @PatchMapping("/users")
    fun updateUser(request: UserUpdateRequest, @LoginUserId id: String) {
        userService.updateById(id, request)
    }

    @LoginValidation
    @DeleteMapping("/users")
    fun deleteUser(@LoginUserId id: String) {
        userService.deleteById(id)
        authService.deauthenticate()
    }
}