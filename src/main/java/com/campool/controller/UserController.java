package com.campool.controller;

import com.campool.model.UserUpdateRequest;
import com.campool.model.UserLoginRequest;
import com.campool.model.UserSignUp;
import com.campool.service.AuthService;
import com.campool.service.UserService;
import javax.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/users/logout")
    public void logoutUser() {
        authService.deauthenticate();
    }

    @PostMapping("/users/{id}")
    public void updateUser(@Valid UserUpdateRequest userUpdateRequest, @PathVariable String id) {
        userService.updateById(id, userUpdateRequest);
    }

}
