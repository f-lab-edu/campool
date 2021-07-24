package com.campool.model

import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class RentalRegisterRequest(
    @NotBlank(message = "제목을 입력해주세요.")
    @Size(max = 255, message = "최대 255자리까지 입력 가능합니다.")
    val title: String,

    @NotBlank(message = "설명을 입력해주세요.")
    @Size(max = 255, message = "최대 255자리까지 입력 가능합니다.")
    val description: String,

    val startDate: LocalDate,

    val endDate: LocalDate,

    val cost: Int,

    val longitude: Double,

    val latitude: Double
)