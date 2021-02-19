package com.campool.service;

import com.campool.model.UserLoginRequest;

public interface AuthService {

    void authenticate(UserLoginRequest userSignUp);

    void deauthenticate();

    boolean isValidAuthentication();

    String getUserId();

}
