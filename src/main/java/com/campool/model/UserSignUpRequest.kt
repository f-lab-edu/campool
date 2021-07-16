package com.campool.model

import com.campool.encrypt.Encryptor
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

class UserSignUpRequest(

    @field:NotBlank(message = "아이디를 입력해주세요.")
    @field:Size(max = 12, message = "최대 12자리까지 입력 가능합니다.")
    val id: String,

    @field:NotBlank(message = "비밀번호를 입력해주세요.")
    @field:Size(max = 20, message = "최대 20자리까지 입력 가능합니다.")
    val password: String,

    @field:NotBlank(message = "이름을 입력해주세요.")
    @field:Size(max = 20, message = "최대 20자리까지 입력 가능합니다.")
    val name: String,

    @field:NotBlank(message = "이메일을 입력해주세요.")
    @field:Size(max = 50, message = "최대 50자리까지 입력 가능합니다.")
    @field:Email(message = "올바른 이메일 형식이 아닙니다.")
    val email: String,

    @field:NotBlank(message = "휴대전화번호를 입력해주세요.")
    @field:Pattern(message = "- 을 제외한 숫자 10자리 또는 11자리를 입력해주세요.", regexp = "[0-9]{10,11}")
    val telephone: String
) {

    fun getEncryptedPasswordUserSignUp(encryptor: Encryptor): UserSignUpRequest {
        return UserSignUpRequest(id, encryptor.encrypt(password), name, email, telephone)
    }
}