package com.campool.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class UserLogin {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(max = 12, message = "최대 12자리까지 입력 가능합니다.")
    private final String id;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(max = 20, message = "최대 20자리까지 입력 가능합니다.")
    private final String password;

}
