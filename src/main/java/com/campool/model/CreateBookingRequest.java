package com.campool.model;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@RequiredArgsConstructor
@ToString
public class CreateBookingRequest {

    @NonNull
    private final long rentalId;

    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate startDate;

    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate endDate;

}
