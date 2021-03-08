package com.campool.model;

import java.time.LocalDate;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@RequiredArgsConstructor
@ToString
public class RentalRegisterRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    @Size(max = 255, message = "최대 255자리까지 입력 가능합니다.")
    private final String title;

    @NotBlank(message = "설명을 입력해주세요.")
    @Size(max = 255, message = "최대 255자리까지 입력 가능합니다.")
    private final String description;

    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate startDate;

    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate endDate;

    @NonNull
    @Min(0)
    private final int cost;

    @NonNull
    private final double longitude;

    @NonNull
    private final double latitude;

}
