package com.campool.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class GearTypeRegisterRequest {

    @NotBlank(message = "캠핑용품 타입을 입력해주세요.")
    @Size(max = 255, message = "최대 255자리까지 입력 가능합니다.")
    private final String name;

}
