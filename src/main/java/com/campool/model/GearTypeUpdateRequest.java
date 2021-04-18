package com.campool.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class GearTypeUpdateRequest {

    @NotBlank(message = "현재 타입 명이 필요합니다.")
    @Size(max = 255, message = "최대 255자리까지 입력 가능합니다.")
    private final String currentName;

    @NotBlank(message = "새로운 타입 명을 입력해주세요.")
    @Size(max = 255, message = "최대 255자리까지 입력 가능합니다.")
    private final String newName;

}
