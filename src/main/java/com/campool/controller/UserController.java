package com.campool.controller;

import com.campool.model.UserSignUp;
import com.campool.service.UserService;
import javax.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    @NonNull
    private final UserService userService;

    @PostMapping("/users")
    public void signUpUser(@Valid UserSignUp userSignUp) {
        userService.add(userSignUp);
    }

}
