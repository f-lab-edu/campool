package com.campool.service;

import com.campool.model.UserSignUp;

public interface AuthService {

    UserSignUp getByIdAndPw(String id, String password);

    void authenticate(UserSignUp userSignUp);

    boolean isValidAuthentication();

}
