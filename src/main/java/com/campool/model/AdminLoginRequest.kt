package com.campool.model

import com.campool.enumeration.Role

class AdminLoginRequest(
    id: String,
    password: String
) : LoginRequest(id, password) {
    override val role = Role.ADMIN
}