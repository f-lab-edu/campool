package com.campool.model;

import com.campool.enumeration.Role;

public class AdminLoginRequest extends LoginRequest {

    public AdminLoginRequest(String id, String password) {
        super(id, password);
    }

    @Override
    public Role getRole() {
        return Role.ADMIN;
    }
}
