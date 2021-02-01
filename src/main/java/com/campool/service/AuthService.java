package com.campool.service;

import com.campool.model.UserSignUp;

public interface AuthService {

    void authenticate(UserSignUp userSignUp);

    boolean isValidAuthentication();

}
