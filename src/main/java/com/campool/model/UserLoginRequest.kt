package com.campool.model

import com.campool.enumeration.Role

class UserLoginRequest(
    id: String,
    password: String
) : LoginRequest(id, password) {
    override val role = Role.USER
}