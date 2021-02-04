package com.campool.service;

import com.campool.model.UserLoginRequest;

public interface AuthService {

    void authenticate(UserLoginRequest userSignUp);

    boolean isValidAuthentication();

}
