package com.campool.model

import com.campool.enumeration.Role
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

abstract class LoginRequest(
    @field:NotBlank(message = "아이디를 입력해주세요.")
    @field:Size(max = 12, message = "최대 12자리까지 입력 가능합니다.")
    val id: String,

    @field:NotBlank(message = "비밀번호를 입력해주세요.")
    @field:Size(max = 20, message = "최대 20자리까지 입력 가능합니다.")
    val password: String
) {
    abstract val role: Role
}