package com.campool.model;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class CreateBookingRequest {

    @NonNull
    private final long rentalId;

    @NonNull
    private final LocalDate startDate;

    @NonNull
    private final LocalDate endDate;

}
