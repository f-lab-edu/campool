package com.campool.service;

import com.campool.enumeration.Role;
import com.campool.model.LoginRequest;

public interface AuthService {

    void authenticate(LoginRequest loginRequest);

    void deauthenticate();

    boolean isValidAuthentication();

    String getAuthenticatedUserId();

    Role getAuthorization();

}
