package com.campool.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class UserUpdateRequest {

    @NotBlank(message = "현재 비밀번호를 입력해주세요.")
    @Size(max = 20, message = "최대 20자리까지 입력 가능합니다.")
    private final String presentPassword;

    @NotBlank(message = "새로운 비밀번호를 입력해주세요.")
    @Size(max = 20, message = "최대 20자리까지 입력 가능합니다.")
    private final String newPassword;

    @NotBlank(message = "이름을 입력해주세요.")
    @Size(max = 20, message = "최대 20자리까지 입력 가능합니다.")
    private final String name;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Size(max = 50, message = "최대 50자리까지 입력 가능합니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private final String email;

    @NotBlank(message = "휴대전화번호를 입력해주세요.")
    @Pattern(regexp = "[0-9]{10,11}", message = "- 을 제외한 숫자 10자리 또는 11자리를 입력해주세요.")
    private final String telephone;

}
