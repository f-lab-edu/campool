package com.campool.controller;

import com.campool.annotation.LoginUserId;
import com.campool.model.UserUpdateRequest;
import com.campool.annotation.LoginValidation;
import com.campool.model.UserLoginRequest;
import com.campool.model.UserSignUp;
import com.campool.service.AuthService;
import com.campool.service.UserService;
import javax.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    @NonNull
    private final UserService userService;

    @NonNull
    private final AuthService authService;

    @PostMapping("/users")
    public void signUpUser(@Valid UserSignUp userSignUp) {
        userService.add(userSignUp);
    }

    @PostMapping("/users/login")
    public void loginUser(@Valid UserLoginRequest userLoginRequest) {
        authService.authenticate(userLoginRequest);
    }

    @LoginValidation
    @GetMapping("/users/logout")
    public void logoutUser() {
        authService.deauthenticate();
    }

    @PatchMapping("/users")
    public void updateUser(@Valid UserUpdateRequest userUpdateRequest, @LoginUserId String id) {
        userService.updateById(id, userUpdateRequest);
    }

    @LoginValidation
    @DeleteMapping("/users")
    public void deleteUser(@LoginUserId String id) {
        userService.deleteById(id);
        authService.deauthenticate();
    }

}
