package com.campool.model;

import com.campool.enumeration.Role;

public class UserLoginRequest extends LoginRequest {

    public UserLoginRequest(String id, String password) {
        super(id, password);
    }

    @Override
    public Role getRole() {
        return Role.USER;
    }
}
